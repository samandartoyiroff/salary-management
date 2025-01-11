package result.agency.result_agency_intern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import result.agency.result_agency_intern.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}