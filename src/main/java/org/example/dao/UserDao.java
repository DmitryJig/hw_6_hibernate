package org.example.dao;

import org.example.models.Order;
import org.example.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll();

    Optional<User> findUserById(Long id);

    List<Order> getOrdersByUserId(Long id);
}
