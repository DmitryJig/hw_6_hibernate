package org.example.dao;

import org.example.models.Order;
import org.example.models.Product;
import org.example.models.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderDao {

    List<Order> findAll();

    Optional<Order> findOrderById(Long id);

    List<Product> getProductsByUserId(Long userId);

    List<User> getUsersByProductId(Long productId);

    Double getPriceByUserAndProduct(Long userId, Long productId);
}
