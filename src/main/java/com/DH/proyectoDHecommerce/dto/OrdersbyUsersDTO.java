package com.DH.proyectoDHecommerce.dto;

public class OrdersbyUsersDTO {

    private Integer idCliente;
    private String nombre;
    private String email;
    private Float ventas;

    public OrdersbyUsersDTO(Integer userId, String userName, String userEmail, Float sales) {
        this.idCliente = userId;
        this.nombre = userName;
        this.email = userEmail;
        this.ventas = sales;
    }

    public OrdersbyUsersDTO() {
    }

    public Integer getUserId() {
        return idCliente;
    }

    public void setUserId(Integer userId) {
        this.idCliente = userId;
    }

    public String getUserName() {
        return nombre;
    }

    public void setUserName(String userName) {
        this.nombre = userName;
    }

    public String getUserEmail() {
        return email;
    }

    public void setUserEmail(String userEmail) {
        this.email = userEmail;
    }

    public Float getSales() {
        return ventas;
    }

    public void setSales(Float sales) {
        this.ventas = sales;
    }
}
