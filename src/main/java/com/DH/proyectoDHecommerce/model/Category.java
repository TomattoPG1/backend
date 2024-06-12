package com.DH.proyectoDHecommerce.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Category")
public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String name;

        @OneToMany(mappedBy = "category")
        private List<Product> products;

        public Category() {
        }

        public Category(Integer id, String name, List<Product> products) {
                this.id = id;
                this.name = name;
                this.products = products;
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

        public List<Product> getProducts() {
                return products;
        }

        public void setProducts(List<Product> products) {
                this.products = products;
        }
}
