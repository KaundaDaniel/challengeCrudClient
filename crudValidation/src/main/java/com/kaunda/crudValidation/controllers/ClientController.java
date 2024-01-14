package com.kaunda.crudValidation.controllers;

import com.kaunda.crudValidation.dto.ClientDto;
import com.kaunda.crudValidation.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto>findById(@PathVariable Long id){
        ClientDto clientDto=clientService.findById(id);
        return ResponseEntity.ok(clientDto);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(Pageable pageable){
        Page<ClientDto> clientDto=clientService.findAll(pageable);
        return ResponseEntity.ok(clientDto);
    }
    @PostMapping()
    public ResponseEntity<ClientDto>save(@Valid @RequestBody ClientDto clientDto){
        clientDto=clientService.save(clientDto);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientDto.getId()).toUri();
        return ResponseEntity.ok(clientDto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDto>update(@PathVariable Long id, @Valid @RequestBody ClientDto clientDto){
        clientDto= clientService.update(id, clientDto);
        return ResponseEntity.ok(clientDto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
