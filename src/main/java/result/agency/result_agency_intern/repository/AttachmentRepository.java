package result.agency.result_agency_intern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import result.agency.result_agency_intern.entity.Attachment;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    Optional<Attachment> findByIdAndDeletedFalse(Long attachmentId);
}