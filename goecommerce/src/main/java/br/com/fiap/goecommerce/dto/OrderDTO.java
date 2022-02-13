package br.com.fiap.goecommerce.dto;

import br.com.fiap.goecommerce.entity.Client;
import br.com.fiap.goecommerce.entity.Product;
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
public class OrderDTO {

    private Long id;
    private Double totalAmmount;
    private OrderStatus orderStatus;

    private ClientDTO client;

    private List<ProductDTO> products;

}
