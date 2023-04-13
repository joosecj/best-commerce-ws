package com.github.bestcommerce.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant saleDate;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderProduct> items = new HashSet<>();

    public Order() {
    }

    public Order(UUID id, Instant saleDate) {
        this.id = id;
        this.saleDate = saleDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Instant saleDate) {
        this.saleDate = saleDate;
    }
}
