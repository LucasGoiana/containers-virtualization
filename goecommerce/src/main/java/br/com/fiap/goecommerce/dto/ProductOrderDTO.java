package br.com.fiap.goecommerce.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ProductOrderDTO {

    private Long id;
    private int quantity;

}
