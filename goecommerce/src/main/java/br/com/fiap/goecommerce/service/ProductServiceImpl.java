package br.com.fiap.goecommerce.service;

import br.com.fiap.goecommerce.configuration.exception.custom.ObjectNotFoundException;
import br.com.fiap.goecommerce.dto.*;
import br.com.fiap.goecommerce.entity.Product;
import br.com.fiap.goecommerce.respository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper,InventoryService inventoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.inventoryService = inventoryService;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return modelMapper.map(getProductEntityById(id),ProductDTO.class);
    }

    @Override
    public ProductResponseCreateDTO createProduct(ProductCreateDTO productCreateDTO) {
        var product = modelMapper.map(productCreateDTO,Product.class);
        var inventories = product.getInventories().stream().peek(inventory -> inventory.setProduct(Arrays.asList(product))).collect(Collectors.toList());
        inventoryService.saveAll(inventories);
        product.setInventories(inventories);
        var productNew = productRepository.save(product);

        return modelMapper.map(productNew, ProductResponseCreateDTO.class);
    }

    @Override
    public ProductDTO updateProduct(Long id ,ProductUpdateDTO productUpdateDTO) {
        var product = getProductEntityById(id);
        product.setName(productUpdateDTO.getName());
        product.setPrice(productUpdateDTO.getPrice());
        var productUpdate = productRepository.save(product);
        return modelMapper.map(productUpdate,ProductDTO.class);
    }

    @Override
    public ProductDTO incrementQuantity(Long id,ProductUpdateQtdDTO productUpdateQtdDTO) {
        var product = getProductEntityById(id);
        var inventoryUpdate = product.getInventories().stream().peek(inventory -> {
            if(inventory.getId() == productUpdateQtdDTO.getInventory().getId()){
                var sum = inventory.getQuantity() + productUpdateQtdDTO.getInventory().getQuantity();
                inventory.setQuantity(sum);
            }
        }).collect(Collectors.toList());
        product.setInventories(inventoryUpdate);

        var productUpdate = productRepository.save(product);
        return modelMapper.map(productUpdate,ProductDTO.class);
    }

    @Override
    public void delete(Long id) {
        var product = getProductEntityById(id);
        productRepository.delete(product);
    }

    @Override
    public List<Product> findAllProductById(List<Long> allIdsProducts) {
        return productRepository.findAllById(allIdsProducts);


    }

    private Product getProductEntityById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado!"));
    }
}
