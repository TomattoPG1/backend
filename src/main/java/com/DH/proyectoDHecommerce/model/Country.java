package com.DH.proyectoDHecommerce.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "country")
    private List<UserAddress> addresses;

    public Country() {
    }

    public Country(Integer id, String name, List<UserAddress> addresses) {
        this.id = id;
        this.name = name;
        this.addresses = addresses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<UserAddress> addresses) {
        this.addresses = addresses;
    }
}
