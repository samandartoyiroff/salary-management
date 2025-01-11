package result.agency.result_agency_intern.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import result.agency.result_agency_intern.dto.ServiceCreateDTO;
import result.agency.result_agency_intern.dto.ServiceUpdateDTO;
import result.agency.result_agency_intern.service.ServiceNameService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/service-name")
public class ServiveNameController {

    private final ServiceNameService serviceNameService;

    @PostMapping
    public ResponseEntity<?> createService(@Valid @RequestBody ServiceCreateDTO serviceCreateDTO){
        return serviceNameService.createService(serviceCreateDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllServiceName(){
        return serviceNameService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceNameById(@PathVariable Long id){
        return serviceNameService.getOneServiceName(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateServiceName(@PathVariable Long id, @Valid @RequestBody ServiceUpdateDTO serviceUpdateDTO){
        return serviceNameService.updateServiceName(id, serviceUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServiceName(@PathVariable Long id){
        return serviceNameService.deleteById(id);
    }

}
