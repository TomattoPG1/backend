package com.DH.proyectoDHecommerce.controller;

import com.DH.proyectoDHecommerce.dto.OrdersDTO;
import com.DH.proyectoDHecommerce.dto.OrdersbyUsersDTO;
import com.DH.proyectoDHecommerce.model.Order;
import com.DH.proyectoDHecommerce.model.OrderItem;
import com.DH.proyectoDHecommerce.model.Product;
import com.DH.proyectoDHecommerce.service.OrderService;
import com.DH.proyectoDHecommerce.service.ProductService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Order order = orderService.getById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/export/csv")
    public ResponseEntity<InputStreamResource> exportOrdersToCsv() throws IOException {
        List<OrdersDTO> orders = orderService.getAllOrdersAsCsv();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            StatefulBeanToCsv<OrdersDTO> beanToCsv = new StatefulBeanToCsvBuilder<OrdersDTO>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(orders);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray()));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=orders.csv")
                .body(resource);
    }


    @GetMapping("/reportSalesByUser/csv")
    public ResponseEntity<InputStreamResource> exportSalesByUserToCsv() throws IOException {
        List<OrdersbyUsersDTO> salesByUsers = orderService.getSalesByUser();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            StatefulBeanToCsv<OrdersbyUsersDTO> beanToCsv = new StatefulBeanToCsvBuilder<OrdersbyUsersDTO>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(salesByUsers);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray()));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ordenesPorCliente.csv")
                .body(resource);
    }

}

