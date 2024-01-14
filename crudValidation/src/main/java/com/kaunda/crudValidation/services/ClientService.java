package com.kaunda.crudValidation.services;

import com.kaunda.crudValidation.dto.ClientDto;
import com.kaunda.crudValidation.entities.Client;
import com.kaunda.crudValidation.repositories.ClientRepository;
import com.kaunda.crudValidation.services.exceptions.DatabaseException;
import com.kaunda.crudValidation.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        var client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não econtrado" + id)
        );
        return new ClientDto(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {
        Page<Client> clientPage = clientRepository.findAll(pageable);
        return clientPage.map(ClientDto::new);
    }

    @Transactional
    public ClientDto save(ClientDto clientDto) {
        var client = new Client();
        convertClientDto(clientDto, client);
        client = clientRepository.save(client);
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto update(Long id, ClientDto clientDto) {
        try {
            var client = clientRepository.getReferenceById(id);
            convertClientDto(clientDto, client);
            client = clientRepository.save(client);
            return new ClientDto(client);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cliente com " + id + "Não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente com " + id + "não encontrado");
        }
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridad referencial");
        }
    }

    private void convertClientDto(ClientDto clientDto, Client client) {
        client.setName(clientDto.getName());
        client.setCpf(client.getCpf());
        client.setIncome(clientDto.getIncome());
        client.setBirthDate(clientDto.getBirthDate());
        client.setChildren(clientDto.getChildren());
    }
}
