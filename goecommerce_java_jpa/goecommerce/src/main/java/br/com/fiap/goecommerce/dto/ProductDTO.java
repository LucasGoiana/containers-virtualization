package br.com.fiap.goecommerce.dto;

import br.com.fiap.goecommerce.entity.Inventory;
import br.com.fiap.goecommerce.entity.Order;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private List<InventoryDTO> inventories;


}
