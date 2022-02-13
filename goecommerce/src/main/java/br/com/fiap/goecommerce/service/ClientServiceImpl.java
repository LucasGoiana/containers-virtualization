package br.com.fiap.goecommerce.service;

import br.com.fiap.goecommerce.configuration.exception.custom.DataIntegrityException;
import br.com.fiap.goecommerce.configuration.exception.custom.ObjectNotFoundException;
import br.com.fiap.goecommerce.dto.*;
import br.com.fiap.goecommerce.entity.Address;
import br.com.fiap.goecommerce.entity.Client;
import br.com.fiap.goecommerce.respository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {


    private final ClientRepository clientRepo;
    private final ModelMapper modelMapper;

    public ClientServiceImpl(ClientRepository clientRepo, ModelMapper modelMapper){
        this.clientRepo = clientRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ClientDTO> getAllClient(){
        List<Client> clients = clientRepo.findAll();
        return clients.stream().map(c -> modelMapper.map(c,ClientDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ClientResponseCreateDTO create(ClientCreateDTO clientCreateDTO){
        var client = modelMapper.map(clientCreateDTO,Client.class);
        client.getAddresses().forEach(address -> address.setClient(client));
        try {
            var clientNew =clientRepo.save(client);
            return modelMapper.map(clientNew,ClientResponseCreateDTO.class);
        }catch (DataIntegrityViolationException e ){
            throw new DataIntegrityException("Cliente ja cadastrado");
        }



    }

    @Override
    public ClientResponseUpdateDTO update(Long id, ClientUpdateDTO clientUpdateDTO) {
        var client = getClientEntityById(id);
        client.setNome(clientUpdateDTO.getNome());
        client.setPhoneNumber(clientUpdateDTO.getPhoneNumber());
        client.setAddresses(clientUpdateDTO.getAddresses().stream()
                        .map(addressDTO -> {
                            var address = modelMapper.map(addressDTO,Address.class);
                            address.setClient(client);
                            return address;
                        })
                        .collect(Collectors.toList()));
        var clientUpdate = clientRepo.save(client);
        return modelMapper.map(clientUpdate,ClientResponseUpdateDTO.class);

    }

    @Override
    public void delete(Long id){
        var client = getClientEntityById(id);
        clientRepo.delete(client);


    }


    @Override
    public ClientDTO getClientById(Long id){
        return modelMapper.map(getClientEntityById(id),ClientDTO.class);
    }

    @Override
    public Client getClientEntityById(Long id){
        return clientRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!"));
    }

}
