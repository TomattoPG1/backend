package com.DH.proyectoDHecommerce.service;

import com.DH.proyectoDHecommerce.model.User;
import com.DH.proyectoDHecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}