package br.com.fiap.goecommerce.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ProductResponseCreateDTO {
    private Long id;
    private String name;
    private Double price;
    private List<InventoryDTO> inventories;

}
