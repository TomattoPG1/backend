package com.DH.proyectoDHecommerce.repository;

import com.DH.proyectoDHecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
