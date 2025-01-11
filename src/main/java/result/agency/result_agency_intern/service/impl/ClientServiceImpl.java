package result.agency.result_agency_intern.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.dto.CleintCreateDTO;
import result.agency.result_agency_intern.dto.ClientResponseDTO;
import result.agency.result_agency_intern.dto.ClientUpdateRequestDTO;
import result.agency.result_agency_intern.entity.Client;
import result.agency.result_agency_intern.entity.ServiceName;
import result.agency.result_agency_intern.repository.ClientRepository;
import result.agency.result_agency_intern.repository.ServiceNameRepository;
import result.agency.result_agency_intern.service.ClientService;
import result.agency.result_agency_intern.util.PhoneService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PhoneService phoneService;
    private final ServiceNameRepository serviceNameRepository;

    @Override
    @Transactional
    public ResponseEntity<?> createClient(CleintCreateDTO cleintCreateDTO) {

        String phoneNumber = phoneService.concatPhoneNumber(cleintCreateDTO.getPhone());
        Optional<Client> optionalClient = clientRepository.findByFullName(cleintCreateDTO.getFullName());
        Optional<Client> optionalClient1 = clientRepository.findByPhoneNumber(phoneNumber);

        if (optionalClient.isEmpty() && optionalClient1.isEmpty()) {
            Client client = Client.builder()
                    .fullName(cleintCreateDTO.getFullName())
                    .phoneNumber(phoneNumber)
                    .build();
            Optional<ServiceName> optionalServiceName =
                    serviceNameRepository.findByIdAndDeletedFalse(cleintCreateDTO.getServiceId());
            if (optionalServiceName.isPresent()) {
                client.setServiceName(optionalServiceName.get());
                clientRepository.save(client);
                return ResponseEntity.ok("Client saved succesfully");
            }
            return ResponseEntity.badRequest().body("ServiceName not found");

        }
        return ResponseEntity.badRequest().body("This client already exists");

    }

    @Override
    public ResponseEntity<?> getAllClients() {
        List<Client> clients = clientRepository.findAllByDeletedFalse();
        List<ClientResponseDTO> clientResponses = new ArrayList<>();
        for (Client client : clients) {

            ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
            clientResponseDTO.setId(client.getId());
            clientResponseDTO.setFullName(client.getFullName());
            clientResponseDTO.setPhoneNumber(client.getPhoneNumber());
            clientResponseDTO.setServiceName(client.getServiceName().getName());
            clientResponses.add(clientResponseDTO);
        }
        return ResponseEntity.ok(clientResponses);
    }

    @Override
    public ResponseEntity<?> getOneClient(Long id) {


        Optional<Client> optionalClient = clientRepository.findByIdAndDeletedFalse(id);
        if (optionalClient.isPresent()){
            ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
            clientResponseDTO.setId(optionalClient.get().getId());
            clientResponseDTO.setFullName(optionalClient.get().getFullName());
            clientResponseDTO.setPhoneNumber(optionalClient.get().getPhoneNumber());
            clientResponseDTO.setServiceName(optionalClient.get().getServiceName().getName());
            return ResponseEntity.ok(clientResponseDTO);
        }
        return ResponseEntity.badRequest().body("Client not found");
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateClient(ClientUpdateRequestDTO clientUpdateRequest, Long id) {

        Optional<Client> optionalClient = clientRepository.findByIdAndDeletedFalse(id);
        Optional<Client> optionalClient1
                = clientRepository.findByPhoneNumberAndDeletedFalse(clientUpdateRequest.getPhoneNumber());
        Optional<Client> optionalClient2
                = clientRepository.findByFullNameAndDeletedFalse(clientUpdateRequest.getFullName());
        if (optionalClient.isPresent()) {
            if (optionalClient1.isPresent()){
                Client client = optionalClient1.get();
                if (!client.getId().equals(optionalClient.get().getId())) throw new  RuntimeException("Client already exist");
            }
            if (optionalClient2.isPresent()){
                Client client = optionalClient2.get();
                if (!client.getId().equals(optionalClient.get().getId())) throw new  RuntimeException("Client already exist");
            }
            Client client = optionalClient.get();
            client.setFullName(clientUpdateRequest.getFullName());
            client.setPhoneNumber(clientUpdateRequest.getPhoneNumber());
            Optional<ServiceName> optionalServiceName = serviceNameRepository.findByIdAndDeletedFalse(clientUpdateRequest.getServiceId());
            if (optionalServiceName.isPresent()) {
                client.setServiceName(optionalServiceName.get());
                clientRepository.save(client);
                return ResponseEntity.ok("Client updated succesfully");
            }
            return ResponseEntity.badRequest().body("ServiceName not found");
        }
        return ResponseEntity.badRequest().body("Client not found");

    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteClient(Long id) {

        Optional<Client> optionalClient = clientRepository.findByIdAndDeletedFalse(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setDeleted(true);
            clientRepository.save(client);
            return ResponseEntity.ok("Client deleted succesfully");
        }
        return ResponseEntity.badRequest().body("Client not found");
    }


}
