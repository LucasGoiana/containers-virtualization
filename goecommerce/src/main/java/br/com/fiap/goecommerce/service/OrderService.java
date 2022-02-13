package br.com.fiap.goecommerce.service;


import br.com.fiap.goecommerce.dto.*;
import br.com.fiap.goecommerce.enumerate.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderResponseCreateDTO createOrder (OrderCreateDTO createOrderDTO);

    OrderResponseDTO getOrderById(Long id);
    List<OrderResponseDTO> getAllOrder();

    OrderResponseDTO updateStatusOrder(Long id, OrderUpdateStatus orderStatus);

}
