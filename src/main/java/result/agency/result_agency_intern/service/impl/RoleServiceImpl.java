package result.agency.result_agency_intern.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.dto.RoleResponseDTO;
import result.agency.result_agency_intern.entity.Role;
import result.agency.result_agency_intern.repository.RoleRepository;
import result.agency.result_agency_intern.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> findAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponseDTO> responseDTOS = new ArrayList<>();
        for (Role role : roles) {
            responseDTOS.add(new RoleResponseDTO(role.getId(), role.getRoleName().name()));
        }
        return ResponseEntity.ok(responseDTOS);
    }
}
