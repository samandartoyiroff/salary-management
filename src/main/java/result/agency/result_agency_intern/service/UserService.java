package result.agency.result_agency_intern.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import result.agency.result_agency_intern.dto.UserCreateDTO;
import result.agency.result_agency_intern.dto.UserUpdateDTO;
import result.agency.result_agency_intern.entity.User;

import java.util.Optional;

public interface UserService {
    ResponseEntity<?> createUser(@Valid UserCreateDTO userCreateDTO);

    ResponseEntity<?> getAllUsers();

    ResponseEntity<?> getOneUser(Long id);

    ResponseEntity<?> updateUser(@Valid UserUpdateDTO userUpdateDTO, Long id);

    ResponseEntity<?> deleteUser(Long id);

    Optional<User> getUserByChatId(Long chatId);
}
