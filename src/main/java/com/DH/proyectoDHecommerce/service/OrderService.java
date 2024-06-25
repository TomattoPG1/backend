package com.DH.proyectoDHecommerce.service;

import com.DH.proyectoDHecommerce.dto.OrdersDTO;
import com.DH.proyectoDHecommerce.dto.OrdersbyUsersDTO;
import com.DH.proyectoDHecommerce.dto.UsersDTO;
import com.DH.proyectoDHecommerce.model.Order;
import com.DH.proyectoDHecommerce.model.OrderItem;
import com.DH.proyectoDHecommerce.model.Product;
import com.DH.proyectoDHecommerce.model.User;
import com.DH.proyectoDHecommerce.repository.OrderRepository;
import com.DH.proyectoDHecommerce.repository.ProductRepository;
import com.DH.proyectoDHecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    public Order getById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Órden no encontrada con el id : " + id));
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


    public List<OrdersbyUsersDTO> getSalesByUser() {
        List<Order> orders = orderRepository.findAll();
        Map<Integer, UsersDTO> usersMap = getUsersMap(); // Obtener un mapa de usuarios para evitar múltiples consultas

        // Agrupar ventas por usuario
        Map<Integer, Float> salesByUser = new HashMap<>();
        for (Order order : orders) {
            Integer userId = order.getUser().getId();
            Float totalSales = salesByUser.getOrDefault(userId, 0.0f);
            totalSales += order.getTotal(); // Sumar el total de la venta
            salesByUser.put(userId, totalSales);
        }

        // Convertir a lista de DTO combinados
        List<OrdersbyUsersDTO> result = new ArrayList<>();
        for (Map.Entry<Integer, Float> entry : salesByUser.entrySet()) {
            Integer userId = entry.getKey();
            Float totalSales = entry.getValue();
            UsersDTO userDTO = usersMap.get(userId);
            OrdersbyUsersDTO dto = new OrdersbyUsersDTO(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), totalSales);
            result.add(dto);
        }

        return result;
    }

    private Map<Integer, UsersDTO> getUsersMap() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .collect(Collectors.toMap(User::getId, user -> new UsersDTO(user.getId(), user.getName(), user.getEmail())));
    }


}
