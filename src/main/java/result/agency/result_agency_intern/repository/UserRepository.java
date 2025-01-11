package result.agency.result_agency_intern.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.dynalink.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import result.agency.result_agency_intern.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Optional<User> findByUsernameAndDeletedFalse(@NotBlank(message = "login cannot be blank") @NotNull(message = "login cannot be null") String login);

    @Query("from User where deleted=false ")
    List<User> findAllNotDeleted();

    Optional<User> findByIdAndDeletedFalse(Long id);

    Optional<User> findByChatIdAndDeletedFalse(Long chatId);
}