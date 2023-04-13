package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.OrderDTO;
import com.github.bestcommerce.entities.Order;
import com.github.bestcommerce.repositories.OrderProductRepository;
import com.github.bestcommerce.repositories.OrderRepository;
import com.github.bestcommerce.repositories.ProductRepository;
import com.github.bestcommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;


    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(UUID id) {
        Order orderEntity = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found"));
        return new OrderDTO(orderEntity);
    }
}
