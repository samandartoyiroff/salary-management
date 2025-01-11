package result.agency.result_agency_intern.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import result.agency.result_agency_intern.entity.Attachment;
import result.agency.result_agency_intern.repository.AttachmentRepository;
import result.agency.result_agency_intern.service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {


    @Value("${file.upload.dir}")
    private String absolutePath;
    private final AttachmentRepository attachmentRepository;


    @Override
    public ResponseEntity<?> saveFile(MultipartFile file) {

        try {
            // Get the file name
            String fileName = file.getOriginalFilename();
            if (fileName==null || fileName.isEmpty()) return ResponseEntity.badRequest().body("File is not valid");
            String format = fileName.substring(fileName.lastIndexOf(".")+1);
            // Ensure the file is not empty
            if (file.isEmpty()) {
                throw new RuntimeException("Cannot store an empty file: " + fileName);
            }
            // Save the file to the target location
            String fullPath = absolutePath + UUID.randomUUID() + "." + format;
            Files.write(Path.of(fullPath), file.getBytes());
            Attachment attachment = new Attachment();
            attachment.setPath(fullPath);
            attachment.setType(format);
            Attachment save = attachmentRepository.save(attachment);
            return ResponseEntity.ok().body(save.getId());
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file. Please try again!", ex);
        }

    }
}
