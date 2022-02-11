package br.com.fiap.goecommerce.controller;


import br.com.fiap.goecommerce.dto.*;
import br.com.fiap.goecommerce.enumerate.OrderStatus;
import br.com.fiap.goecommerce.service.OrderService;
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
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de pedidos"),
    })
    @ApiOperation(value = "Lista todos os pedidos")
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
        List<OrderResponseDTO> orderResponseDTOS = orderService.getAllOrder();
        return ResponseEntity.ok(orderResponseDTOS);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o pedido"),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
    })
    @ApiOperation(value = "Retorna o pedido buscado pelo ID")
    @GetMapping("{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(
            @PathVariable Long id
    ){
        var orderResponseDTO = orderService.getOrderById(id);
        return ResponseEntity.ok(orderResponseDTO);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pedido criado"),
    })
    @ApiOperation("Cria um pedido")
    @PostMapping
    public ResponseEntity<OrderResponseCreateDTO> createOrder(
            @RequestBody OrderCreateDTO orderCreateDTO
    ){
        var orderResponse = orderService.createOrder(orderCreateDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(orderResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(orderResponse);

    }

    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido Atualizado"),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
    })
    @ApiOperation("Atualiza o status do pedido")
    @PutMapping("{id}/status")
    public ResponseEntity<OrderResponseDTO> updateStatusOrder(
            @PathVariable Long id,
            @RequestBody OrderUpdateStatus orderStatus
    ){
        var orderResponse = orderService.updateStatusOrder(id,orderStatus);
        return ResponseEntity.ok(orderResponse);

    }

}
