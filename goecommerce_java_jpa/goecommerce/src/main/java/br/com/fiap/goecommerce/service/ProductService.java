package br.com.fiap.goecommerce.service;

import br.com.fiap.goecommerce.dto.*;
import br.com.fiap.goecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProduct();
    ProductDTO getProductById(Long id);
    ProductResponseCreateDTO createProduct(ProductCreateDTO product);
    ProductDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO);
    ProductDTO incrementQuantity(Long id ,ProductUpdateQtdDTO productUpdateQtdDTO);
    void delete(Long id);

    List<Product> findAllProductById(List<Long> allIdsProducts);
}
