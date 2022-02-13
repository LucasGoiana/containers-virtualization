package br.com.fiap.goecommerce.dto;

import br.com.fiap.goecommerce.enumerate.OrderStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class OrderUpdateStatus {
    private OrderStatus orderStatus;

}
