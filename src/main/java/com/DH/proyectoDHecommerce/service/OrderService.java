package com.DH.proyectoDHecommerce.service;

import com.DH.proyectoDHecommerce.dto.OrdersDTO;
import com.DH.proyectoDHecommerce.model.Order;
import com.DH.proyectoDHecommerce.model.OrderItem;
import com.DH.proyectoDHecommerce.model.Product;
import com.DH.proyectoDHecommerce.repository.OrderRepository;
import com.DH.proyectoDHecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    public Order getById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + id));
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public List<OrdersDTO> getAllOrdersAsCsv() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToOrderCsvDto)
                .collect(Collectors.toList());
    }

    private OrdersDTO convertToOrderCsvDto(Order order) {
        OrdersDTO dto = new OrdersDTO();
        dto.setId(order.getId());
        dto.setSubTotal(order.getSubTotal());
        dto.setTax(order.getTax());
        dto.setTotal(order.getTotal());
        dto.setItemsInOrder(order.getItemsInOrder());
        dto.setPaid(order.getPaid());
        dto.setPaidAt(order.getPaidAt());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());
        dto.setUserId(order.getUser().getId());
        return dto;
    }

    public void processOrder(Order order) {

        List<OrderItem> orderItems = order.getItems();

        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            int orderedQuantity = orderItem.getQuantity();
            int currentStock = product.getInStock();

            if (currentStock < orderedQuantity) {
                throw new RuntimeException("Stock insuficiente para el producto: " + product.getTitle());
            }

            int updatedStock = currentStock - orderedQuantity;
            product.setInStock(updatedStock);
            productService.updateProduct(product);
        }}

}
