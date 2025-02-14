package result.agency.result_agency_intern.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import result.agency.result_agency_intern.dto.UserCreateDTO;
import result.agency.result_agency_intern.dto.UserUpdateDTO;
import result.agency.result_agency_intern.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO){
        return userService.createUser(userCreateDTO);
    }
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return userService.getOneUser(id);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO userUpdateDTO){
        return userService.updateUser(userUpdateDTO, id);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
