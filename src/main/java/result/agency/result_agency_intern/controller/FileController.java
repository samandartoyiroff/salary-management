package result.agency.result_agency_intern.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import result.agency.result_agency_intern.entity.Attachment;
import result.agency.result_agency_intern.repository.AttachmentRepository;
import result.agency.result_agency_intern.service.FileService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileController {

    @Value("${file.upload.dir}")
    private String url;

    private final FileService fileService;
    private final AttachmentRepository attachmentRepository;

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> uploadFile(@RequestParam(name = "file") MultipartFile file ) {
        return fileService.saveFile(file);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<?> downloadFile(@PathVariable Long fileId) {
        try {
            Optional<Attachment> optionalAttachment = attachmentRepository.findByIdAndDeletedFalse(fileId);
            if (optionalAttachment.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Path filePath = Paths.get(url + optionalAttachment.get().getPath()).toAbsolutePath().normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
