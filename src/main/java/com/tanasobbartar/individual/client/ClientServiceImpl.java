package com.tanasobbartar.individual.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ClientDto findById(Long id) {
        return clientRepository.findById(id).map(this::toDto).orElseThrow();
    }

    @Override
    public ClientDto saveClient(NewClientDto clientDto) {
        Client client = new Client();
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setUsername(clientDto.getUsername());
        client.setBirthdate(clientDto.getBirthdate());
        client.setNationalCode(clientDto.getNationalCode());
        client.setGenderType(clientDto.getGenderType());
        Client savedClient = clientRepository.save(client);
        return toDto(savedClient);
    }

    private ClientDto toDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .username(client.getUsername())
                .birthdate(client.getBirthdate())
                .nationalCode(client.getNationalCode())
                .genderType(client.getGenderType())
                .build();
    }
}
