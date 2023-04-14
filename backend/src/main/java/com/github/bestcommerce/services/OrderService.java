package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.OrderDTO;
import com.github.bestcommerce.dtos.v1.OrderProductDTO;
import com.github.bestcommerce.entities.Order;
import com.github.bestcommerce.entities.OrderProduct;
import com.github.bestcommerce.entities.Product;
import com.github.bestcommerce.entities.User;
import com.github.bestcommerce.repositories.OrderProductRepository;
import com.github.bestcommerce.repositories.OrderRepository;
import com.github.bestcommerce.repositories.ProductRepository;
import com.github.bestcommerce.repositories.UserRepository;
import com.github.bestcommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserService userService;


    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository,
                        UserService userService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(UUID id) {
        Order orderEntity = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found"));
        return new OrderDTO(orderEntity);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order orderEntity = new Order();
        orderEntity.setSaleDate(Instant.now());
        User userEntity = userService.authentiated();
        orderEntity.setClient(userEntity);
        for (OrderProductDTO itemDto : dto.getItems()) {
            Product productEntity = productRepository.getReferenceById(itemDto.getProductId());
            OrderProduct orderItemEntity = new OrderProduct(orderEntity, productEntity, itemDto.getQuantity(),
                    productEntity.getPrice(), itemDto.getTax());
            orderEntity.getItems().add(orderItemEntity);
        }
        orderRepository.save(orderEntity);
        orderProductRepository.saveAll(orderEntity.getItems());
        return new OrderDTO(orderEntity);
    }
}
