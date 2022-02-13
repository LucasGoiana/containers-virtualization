package br.com.fiap.goecommerce.controller;

import br.com.fiap.goecommerce.dto.*;
import br.com.fiap.goecommerce.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de produtos"),
    })
    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> products= productService.getAllProduct();
        return ResponseEntity.ok(products);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o produto"),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
    })
    @ApiOperation(value = "Retorna o produto pelo ID")
    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProductById(
            @PathVariable Long id
    ){
        ProductDTO product= productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto atualizado"),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
    })
    @ApiOperation(value = "Atualiza um produto")
    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateDTO productUpdateDTO
    ){
        var productResponse = productService.updateProduct(id, productUpdateDTO);
        return ResponseEntity.ok(productResponse);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto criado"),
    })
    @ApiOperation(value = "Cria um produto")
    @PostMapping
    public ResponseEntity<ProductResponseCreateDTO> createProduct(
            @RequestBody ProductCreateDTO productCreateDTO
    ){

        ProductResponseCreateDTO productResponse = productService.createProduct(productCreateDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(productResponse.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(productResponse);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Quantidade de produto adicionada em estoque"),
            @ApiResponse(code = 404, message = "Produto e/ou estoque não encontrado"),
    })

    @ApiOperation(value = "Adiciona uma quantidade do produto em estoque")
    @PutMapping("{id}/incrementQty")
    public ResponseEntity<ProductDTO> updateQuantityProduct(
           @ApiParam(value = "ID do produto", required = true) @PathVariable Long id,
           @RequestBody ProductUpdateQtdDTO productUpdateQtdDTO
    ){
        var productResponse = productService.incrementQuantity(id, productUpdateQtdDTO);
        return ResponseEntity.ok(productResponse);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Produto deletado"),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
    })
    @ApiOperation(value = "Deleta o produto")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
