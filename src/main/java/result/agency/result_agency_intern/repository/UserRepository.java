package result.agency.result_agency_intern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import result.agency.result_agency_intern.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}