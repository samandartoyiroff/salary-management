package result.agency.result_agency_intern.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import result.agency.result_agency_intern.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByIdAndDeletedFalse(@NotNull(message = "roleId cannot be null") Long roleId);
}