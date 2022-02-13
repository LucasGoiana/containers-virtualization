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
public class OrderCreateDTO {

    private Long clientId;

    private List<ProductOrderDTO> products;

}
