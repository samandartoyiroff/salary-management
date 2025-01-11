package result.agency.result_agency_intern.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.dto.UserCreateDTO;
import result.agency.result_agency_intern.dto.UserResponseDTO;
import result.agency.result_agency_intern.dto.UserUpdateDTO;
import result.agency.result_agency_intern.entity.Role;
import result.agency.result_agency_intern.entity.User;
import result.agency.result_agency_intern.repository.RoleRepository;
import result.agency.result_agency_intern.repository.UserRepository;
import result.agency.result_agency_intern.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ResponseEntity<?> createUser(UserCreateDTO userCreateDTO) {

        if (userCreateDTO.getPassword().length()<8) return ResponseEntity.internalServerError().body("Password must be at least 8 characters");
        Optional<User> optionalUser
                = userRepository.findByUsernameAndDeletedFalse(userCreateDTO.getLogin());
        if (optionalUser.isPresent()) return ResponseEntity.badRequest().body("User already exists");

        Optional<Role> optionalRole = roleRepository.findByIdAndDeletedFalse(userCreateDTO.getRoleId());
        if (optionalRole.isEmpty()) return ResponseEntity.badRequest().body("Role not found");
        User user = User.builder()
                .role(optionalRole.get())
                .username(userCreateDTO.getLogin())
                .password(passwordEncoder.encode(userCreateDTO.getPassword()))
                .fullName(userCreateDTO.getFullName())
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("User created successfully!!!");
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        List<UserResponseDTO> userResponses = new ArrayList<>();
        List<User> users = userRepository.findAllNotDeleted();
        for (User user : users) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setRole(user.getRole().getRoleName().name());
            userResponseDTO.setFullName(user.getFullName());
            userResponseDTO.setPhoneNumber(user.getPhoneNumber());
            userResponseDTO.setChatId(user.getChatId());
            userResponseDTO.setLogin(user.getUsername());
            userResponses.add(userResponseDTO);
        }
        return ResponseEntity.ok(userResponses);
    }

    @Override
    public ResponseEntity<?> getOneUser(Long id) {

        Optional<User> optionalUser = userRepository.findByIdAndDeletedFalse(id);
        if (optionalUser.isPresent()) {

            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(optionalUser.get().getId());
            userResponseDTO.setRole(optionalUser.get().getRole().getRoleName().name());
            userResponseDTO.setFullName(optionalUser.get().getFullName());
            userResponseDTO.setPhoneNumber(optionalUser.get().getPhoneNumber());
            userResponseDTO.setChatId(optionalUser.get().getChatId());
            userResponseDTO.setLogin(optionalUser.get().getUsername());
            return ResponseEntity.ok(userResponseDTO);
        }
        return ResponseEntity.ok("User not found");


    }

    @Override
    @Transactional
    public ResponseEntity<?> updateUser(UserUpdateDTO userUpdateDTO, Long id) {

        Optional<User> optionalUser = userRepository.findByIdAndDeletedFalse(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Role> optionalRole = roleRepository.findByIdAndDeletedFalse(userUpdateDTO.getRoleId());
            if (optionalRole.isEmpty()) return ResponseEntity.badRequest().body("Role not found");
            user.setRole(optionalRole.get());
            userRepository.save(user);
            return ResponseEntity.ok("User updated successfully!!!");
        }
        return ResponseEntity.ok("User not found");

    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findByIdAndDeletedFalse(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setDeleted(true);
            userRepository.save(user);
        }
        return ResponseEntity.badRequest().body("User not found!!!");
    }

    @Override
    public Optional<User> getUserByChatId(Long chatId) {
       return userRepository.findByChatIdAndDeletedFalse(chatId);
    }


}
