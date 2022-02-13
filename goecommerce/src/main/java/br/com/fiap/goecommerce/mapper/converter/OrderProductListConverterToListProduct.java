package br.com.fiap.goecommerce.mapper.converter;

import br.com.fiap.goecommerce.dto.InventoryDTO;
import br.com.fiap.goecommerce.dto.ProductDTO;
import br.com.fiap.goecommerce.dto.ProductResponseCreateOrderDTO;
import br.com.fiap.goecommerce.entity.OrderProduct;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.stream.Collectors;

public class OrderProductListConverterToListProduct extends AbstractConverter<List<OrderProduct> ,List<ProductResponseCreateOrderDTO>> {

    @Override
    protected List<ProductResponseCreateOrderDTO> convert(List<OrderProduct> orderProducts) {
        return orderProducts.stream().map(orderProduct -> {
            var product = orderProduct.getProduct();
            var productResponse = new ProductResponseCreateOrderDTO();
            productResponse.setPrice(product.getPrice());
            productResponse.setName(product.getName());
            productResponse.setId(product.getId());
            productResponse.setQuantity(orderProduct.getQuantity());
            return productResponse;
        }).collect(Collectors.toList());

    }
}
