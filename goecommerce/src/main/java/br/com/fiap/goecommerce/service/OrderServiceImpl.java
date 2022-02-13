package br.com.fiap.goecommerce.service;


import br.com.fiap.goecommerce.configuration.exception.custom.ObjectNotFoundException;
import br.com.fiap.goecommerce.configuration.exception.custom.ProductInventoryUnavailableException;
import br.com.fiap.goecommerce.dto.*;
import br.com.fiap.goecommerce.entity.Client;
import br.com.fiap.goecommerce.enumerate.OrderStatus;
import br.com.fiap.goecommerce.mapper.converter.OrderProductListConverterToListProduct;
import br.com.fiap.goecommerce.entity.Order;
import br.com.fiap.goecommerce.entity.OrderProduct;
import br.com.fiap.goecommerce.entity.Product;
import br.com.fiap.goecommerce.respository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {


    private final ClientService clientService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(ClientService clientService, ProductService productService, OrderRepository orderRepository, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;

        modelMapper.typeMap(Order.class, OrderResponseCreateDTO.class).addMappings(mapper -> {
            mapper.using(new OrderProductListConverterToListProduct()).map(Order::getOrderProducts,OrderResponseCreateDTO::setProducts);
        });

        modelMapper.typeMap(Order.class, OrderResponseDTO.class).addMappings(mapper -> {
            mapper.using(new OrderProductListConverterToListProduct()).map(Order::getOrderProducts,OrderResponseDTO::setProducts);
        });

    }

    @Override
    public OrderResponseCreateDTO createOrder(OrderCreateDTO createOrderDTO) {
        var client = clientService.getClientEntityById(createOrderDTO.getClientId());
        var allIdsProducts = createOrderDTO.getProducts().stream().map(product ->  product.getId()).collect(Collectors.toList());
        var products = productService.findAllProductById(allIdsProducts);
        if(allIdsProducts.size() != products.size()){
            throw new ObjectNotFoundException("Algum dos produtos informado não foi encontrado!");
        }

        Order order = new Order();
        reduceQuantityInInventoryProduct(createOrderDTO, products);
        order.setTotalAmmount(calculateTotalAmmount(createOrderDTO, products));
        order.setClient(client);
        setOrderProduct(createOrderDTO, products, order);
        order.setOrderStatus(OrderStatus.PENDENT);

        var newOrder = orderRepository.save(order);

        return modelMapper.map(order,OrderResponseCreateDTO.class);

    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        return modelMapper.map(getOrderEntityById(id),OrderResponseDTO.class);
    }

    @Override
    public List<OrderResponseDTO> getAllOrder() {
        List<Order> orders = orderRepository.findAll();


        return orders
                .stream()
                .map(order -> modelMapper.map(order,OrderResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO updateStatusOrder(Long id, OrderUpdateStatus orderStatus) {
        var order = getOrderEntityById(id);
        order.setOrderStatus(orderStatus.getOrderStatus());
        var orderUpdate = orderRepository.save(order);
        return modelMapper.map(order,OrderResponseDTO.class);
    }

    private void setOrderProduct(OrderCreateDTO createOrderDTO, List<Product> products, Order order) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        products.forEach(product -> {
            createOrderDTO.getProducts().forEach(itemOrder ->
            {
                if(product.getId() == itemOrder.getId()){

                            orderProducts.add( OrderProduct.builder()
                                    .order(order)
                                    .product(product)
                                    .quantity(itemOrder.getQuantity()).build());
                }
            });
        });
        order.setOrderProducts(orderProducts);
    }

    private void reduceQuantityInInventoryProduct(OrderCreateDTO createOrderDTO, List<Product> products) {
        products.forEach(product -> {
                createOrderDTO.getProducts().forEach(orderProduct -> {
                    if(product.getId() == orderProduct.getId()){
                        var inventoryProduct = product.getInventories().stream()
                                .filter(inventory -> inventory.getQuantity() >= orderProduct.getQuantity())
                                .findFirst()
                                .orElseThrow(() -> new ProductInventoryUnavailableException("Produto indisponivel no momento"));

                        product.getInventories()
                                .get(product.getInventories().indexOf(inventoryProduct))
                                .setQuantity(inventoryProduct.getQuantity() - orderProduct.getQuantity());
                    }
                });
        });
    }

    private Double calculateTotalAmmount(OrderCreateDTO createOrderDTO, List<Product> products) {
        AtomicReference<Double> sum = new AtomicReference<>(0.00);
        products.forEach(product -> {
            createOrderDTO.getProducts().forEach(orderProduct -> {
                if(product.getId() == orderProduct.getId()){
                    sum.updateAndGet(v -> v + (product.getPrice() * orderProduct.getQuantity()));
                }
            });
        });
        return sum.get();
    }

    private Order getOrderEntityById(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pedido não foi encontrado!"));
    }
}
