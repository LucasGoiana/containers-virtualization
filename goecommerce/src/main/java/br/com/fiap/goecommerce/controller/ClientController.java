package br.com.fiap.goecommerce.controller;

import br.com.fiap.goecommerce.dto.*;
import br.com.fiap.goecommerce.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {


    private final ClientService serv;


    public ClientController(ClientService serv) {
        this.serv = serv;
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de clientes"),
    })
    @GetMapping()
    public ResponseEntity<List<ClientDTO>> getAllClient(){
        List<ClientDTO> clientDTOS= serv.getAllClient();
        return ResponseEntity.ok(clientDTOS);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cliente"),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
    })
    @ApiOperation(value = "Retorna o cliente buscado pelo ID")
    @GetMapping("{id}")
    public ResponseEntity<ClientDTO> getClientById(
            @PathVariable Long id
    ){
        ClientDTO clientDTO= serv.getClientById(id);
        return ResponseEntity.ok(clientDTO);
    }
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation("Cria um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente criado"),
    })
    @PostMapping
    public ResponseEntity<ClientResponseCreateDTO> createClient(
            @RequestBody ClientCreateDTO clientCreateDTO
    ){

        ClientResponseCreateDTO clientResponse = serv.create(clientCreateDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(clientResponse.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(clientResponse);
    }
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation("Atualiza as informações de cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),

    })
    @PutMapping("{id}")
    public ResponseEntity<ClientResponseUpdateDTO> updateClient(
            @PathVariable Long id,
            @RequestBody ClientUpdateDTO clientUpdateDTO
    ){

        ClientResponseUpdateDTO clientResponse = serv.update(id,clientUpdateDTO);

        return ResponseEntity.ok(clientResponse);

    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cliente deletado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(
            @PathVariable Long id
    ){
        serv.delete(id);
        return ResponseEntity.noContent().build();
    }



}
