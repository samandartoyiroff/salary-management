package result.agency.result_agency_intern.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import result.agency.result_agency_intern.dto.CleintCreateDTO;
import result.agency.result_agency_intern.dto.ClientUpdateRequestDTO;
import result.agency.result_agency_intern.service.ClientService;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody CleintCreateDTO cleintCreateDTO){
        return clientService.createClient(cleintCreateDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id){
        return clientService.getOneClient(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@Valid @RequestBody ClientUpdateRequestDTO clientUpdateRequest, @PathVariable Long id){
        return clientService.updateClient(clientUpdateRequest, id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id){
        return clientService.deleteClient(id);
    }


}
