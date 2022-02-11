package br.com.fiap.goecommerce.service;

import br.com.fiap.goecommerce.dto.*;
import br.com.fiap.goecommerce.entity.Client;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClient();
    ClientResponseCreateDTO create(ClientCreateDTO clientCreateDTO);
    ClientResponseUpdateDTO update(Long id, ClientUpdateDTO clientUpdateDTO);
    void delete(Long id);
    ClientDTO getClientById(Long id);
    Client getClientEntityById(Long id);
}
