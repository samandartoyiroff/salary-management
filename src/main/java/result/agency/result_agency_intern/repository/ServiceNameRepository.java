package result.agency.result_agency_intern.repository;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import result.agency.result_agency_intern.entity.ServiceName;

import java.util.List;
import java.util.Optional;

public interface ServiceNameRepository extends JpaRepository<ServiceName, Long> {
    Optional<ServiceName> findByName(@NonNull @NotBlank(message="name cannot be blank") String name);

    @Query("from ServiceName where deleted = false ")
    List<ServiceName> findAllNotDeleted();

    Optional<ServiceName> findByIdAndDeletedFalse(Long id);
}