package com.tanasobbartar.individual.client;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<ClientDto> addClient(@RequestBody NewClientDto newClientDto) {
        ClientDto savedClient = clientService.saveClient(newClientDto);
        return ResponseEntity.created(URI.create("/clients/" + savedClient.getId())).body(savedClient);
    }

}
