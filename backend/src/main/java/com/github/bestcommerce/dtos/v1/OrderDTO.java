package com.github.bestcommerce.dtos.v1;

import com.github.bestcommerce.entities.Order;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDTO {
    private UUID id;
    private Instant moment;
    private UserMinDTO client;
    @NotEmpty(message = "Must have at least one item")
    private List<OrderProductDTO> items = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(UUID id, Instant moment, UserMinDTO client) {
        this.id = id;
        this.moment = moment;
        this.client = client;
    }

    public OrderDTO(Order orderEnity) {
        id = orderEnity.getId();
        moment = orderEnity.getSaleDate();
        client = new UserMinDTO(orderEnity.getClient());
        items = orderEnity.getItems().stream().map(OrderProductDTO::new).toList();
    }

    public UUID getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public UserMinDTO getClient() {
        return client;
    }

    public List<OrderProductDTO> getItems() {
        return items;
    }
}
