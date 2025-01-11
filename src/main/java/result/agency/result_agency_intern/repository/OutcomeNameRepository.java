package result.agency.result_agency_intern.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import result.agency.result_agency_intern.entity.OutcomeName;

import java.util.List;
import java.util.Optional;

public interface OutcomeNameRepository extends JpaRepository<OutcomeName, Long> {
    Optional<OutcomeName> findByNameAndDeletedFalse(@NotBlank(message = "name cannot be blank") @NotNull(message = "name cannot be null") String name);

    List<OutcomeName> findAllByDeletedFalse();

    Optional<OutcomeName> findByIdAndDeletedFalse(Long id);
}