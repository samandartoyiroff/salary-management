package result.agency.result_agency_intern.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.dto.ServiceCreateDTO;
import result.agency.result_agency_intern.dto.ServiceResponseDTO;
import result.agency.result_agency_intern.dto.ServiceUpdateDTO;
import result.agency.result_agency_intern.entity.ServiceName;
import result.agency.result_agency_intern.repository.ServiceNameRepository;
import result.agency.result_agency_intern.service.ServiceNameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ServiceNameServiceImpl implements ServiceNameService {

    @Autowired
    private ServiceNameRepository svcNameRepository;


    @Override
    @Transactional
    public ResponseEntity<?> createService(ServiceCreateDTO serviceCreateDTO) {
        Optional<ServiceName> optional = svcNameRepository.findByName(serviceCreateDTO.getName());
        if (optional.isPresent()) throw new RuntimeException("This service already exists");
        ServiceName serviceName = ServiceName.builder()
                .name(serviceCreateDTO.getName())
                .build();
        svcNameRepository.save(serviceName);
        return ResponseEntity.ok("Created service " + serviceName.getName());

    }

    @Override
    public ResponseEntity<?> getAllServices() {
        List<ServiceName> services = svcNameRepository.findAllNotDeleted();
        List<ServiceResponseDTO> serviceResponseDTOs = new ArrayList<>();
        for (ServiceName service : services) {
            ServiceResponseDTO responseDTO = new ServiceResponseDTO();
            responseDTO.setName(service.getName());
            responseDTO.setId(service.getId());
            serviceResponseDTOs.add(responseDTO);
        }
        return ResponseEntity.ok(serviceResponseDTOs);
    }

    @Override
    public ResponseEntity<?> getOneServiceName(Long id) {
        Optional<ServiceName> serviceName = svcNameRepository.findByIdAndDeletedFalse(id);
        if (serviceName.isPresent()) {
            ServiceResponseDTO serviceResponseDTO = new ServiceResponseDTO();
            serviceResponseDTO.setName(serviceName.get().getName());
            serviceResponseDTO.setId(serviceName.get().getId());
            return ResponseEntity.ok(serviceResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateServiceName(Long id, ServiceUpdateDTO serviceUpdateDTO) {

        Optional<ServiceName> serviceName = svcNameRepository.findByIdAndDeletedFalse(id);
        if (serviceName.isPresent()) {
            ServiceName service = serviceName.get();
            service.setName(serviceUpdateDTO.getName());
            svcNameRepository.save(service);
            return ResponseEntity.ok("Updated service " + service.getName());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long id) {


        Optional<ServiceName> optionalServiceName = svcNameRepository.findByIdAndDeletedFalse(id);
        if (optionalServiceName.isPresent()) {
            ServiceName serviceName = optionalServiceName.get();
            serviceName.setDeleted(true);
            svcNameRepository.save(serviceName);
            return ResponseEntity.ok("Deleted service " + serviceName.getName());
        }
        return ResponseEntity.notFound().build();
    }
}
