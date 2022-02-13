package br.com.fiap.goecommerce.dto;

import br.com.fiap.goecommerce.enumerate.OrderStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class OrderResponseCreateDTO {

    private Long id;
    private Double totalAmmount;
    private OrderStatus orderStatus;

    private ClientResponseCreateOrderDTO client;

    private List<ProductResponseCreateOrderDTO> products;

}
