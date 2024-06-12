package com.DH.proyectoDHecommerce.dto;

import java.sql.Timestamp;

public class OrdersDTO {
    private Integer id;
    private Float subTotal;
    private Float tax;
    private Float total;
    private Integer itemsInOrder;
    private Boolean isPaid;
    private Timestamp paidAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer userId;

    public OrdersDTO(Integer id, Float subTotal, Float tax, Float total, Integer itemsInOrder, Boolean isPaid, Timestamp paidAt, Timestamp createdAt, Timestamp updatedAt, Integer userId) {
        this.id = id;
        this.subTotal = subTotal;
        this.tax = tax;
        this.total = total;
        this.itemsInOrder = itemsInOrder;
        this.isPaid = isPaid;
        this.paidAt = paidAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    public OrdersDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Integer getItemsInOrder() {
        return itemsInOrder;
    }

    public void setItemsInOrder(Integer itemsInOrder) {
        this.itemsInOrder = itemsInOrder;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Timestamp getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Timestamp paidAt) {
        this.paidAt = paidAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

