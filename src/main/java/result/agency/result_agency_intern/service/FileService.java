package result.agency.result_agency_intern.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResponseEntity<?> saveFile(MultipartFile file);
}
