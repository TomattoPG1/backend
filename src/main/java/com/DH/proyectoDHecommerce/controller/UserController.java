package com.DH.proyectoDHecommerce.controller;

import com.DH.proyectoDHecommerce.model.User;
import com.DH.proyectoDHecommerce.model.UserAddress;
import com.DH.proyectoDHecommerce.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"lista_clientes.csv\"");

        List<User> users = userService.getAllUsers();

        PrintWriter writer = response.getWriter();
        writer.println("ID cliente,Nombre de cliente,Email,Rol del usuario,Id de Domicilio,Nombre del cliente,Apellido del cliente,Domicilio,Domicilio secundario,Código Postal,Teléfono,Ciudad,País"); // Header del CSV

        for (User user : users) {
            List<UserAddress> addresses = user.getAddresses();
            if (addresses != null && !addresses.isEmpty()) {
                for (UserAddress address : addresses) {
                    writer.println(user.getId() + "," +
                            user.getName() + "," +
                            user.getEmail() + "," +
                            user.getRole() + "," +
                            address.getId() + "," +
                            address.getFirstName() + "," +
                            address.getLastName() + "," +
                            address.getAddress() + "," +
                            address.getAddress2() + "," +
                            address.getPostalCode() + "," +
                            address.getPhone() + "," +
                            address.getCity() + "," +
                            address.getCountry().getName());
                }
            } else {
                writer.println(user.getId() + "," +
                        user.getName() + "," +
                        user.getEmail() + "," +
                        user.getRole() + ",,,,,,,");
            }
        }
    }
}