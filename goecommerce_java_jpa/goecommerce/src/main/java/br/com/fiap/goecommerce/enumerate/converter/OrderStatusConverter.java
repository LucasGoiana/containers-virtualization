package br.com.fiap.goecommerce.enumerate.converter;

import br.com.fiap.goecommerce.enumerate.OrderStatus;
import br.com.fiap.goecommerce.enumerate.Profile;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus,Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatus orderStatus) {
        if(orderStatus == null){
            return null;
        }
        return orderStatus.getCod();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }

        return Stream.of(OrderStatus.values())
                .filter(orderStatus -> orderStatus.getCod().equals(code))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
