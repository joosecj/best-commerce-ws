package com.github.bestcommerce.dtos.v1;

import com.github.bestcommerce.entities.OrderProduct;

import java.util.UUID;

public class OrderProductDTO {
    private UUID productId;
    private String name;
    private Double price;
    private Integer quantity;
    private Integer tax;
    private String imgUrl;

    public OrderProductDTO() {
    }

    public OrderProductDTO(UUID productId, String name, Double price, Integer quantity, Integer tax, String imgUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.tax = tax;
        this.imgUrl = imgUrl;
    }

    public OrderProductDTO(OrderProduct orderProductEntity) {
        productId = orderProductEntity.getProduct().getId();
        name = orderProductEntity.getProduct().getName();
        price = orderProductEntity.getPrice();
        quantity = orderProductEntity.getQuantity();
        tax = orderProductEntity.getTax();
        imgUrl = orderProductEntity.getProduct().getImgUrl();
    }

    public UUID getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getTax() {
        return tax;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Double getSubtotal() {
        return price * quantity + tax;
    }
}
