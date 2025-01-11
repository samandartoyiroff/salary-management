package result.agency.result_agency_intern.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import result.agency.result_agency_intern.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByFullName(@NotBlank(message = "fullname cannot be blank") @NotNull(message = "fullname cannot be null") String fullName);

    Optional<Client> findByPhoneNumber(@NotBlank(message = "phone cannot be blank") @NotNull(message = "phone cannot be null") String phone);

    List<Client> findAllByDeletedFalse();

    Optional<Client> findByIdAndDeletedFalse(Long id);

    Optional<Client> findByPhoneNumberAndDeletedFalse(@NotNull(message = "phone canoot be null") @NotBlank(message = "phone canoot be blank") String phoneNumber);

    Optional<Client> findByFullNameAndDeletedFalse(@NotNull(message = "fullname canoot be null") @NotBlank(message = "fullname canoot be blank") String fullName);

}