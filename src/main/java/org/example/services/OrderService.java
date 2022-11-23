package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dao.OrderDao;
import org.example.models.Order;
import org.example.models.Product;
import org.example.models.User;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDao orderDao;

    public List<Order> findAll() {
        return orderDao.findAll();
    }

    public List<Product> getProductsByUserId(Long userId) {
        return orderDao.getProductsByUserId(userId);
    }

    public List<User> getUsersByProductId(Long productId) {
        return orderDao.getUsersByProductId(productId);
    }

    public Double getPriceByUserAndProduct(Long userId, Long productId) {
        return orderDao.getPriceByUserAndProduct(userId, productId);
    }
}
