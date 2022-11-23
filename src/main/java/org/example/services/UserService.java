package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dao.UserDao;
import org.example.models.Order;
import org.example.models.User;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public Optional<User> findUserById(Long id) {
        return userDao.findUserById(id);
    }

    public List<Order> getOrdersByUserId(Long id) {
        return userDao.getOrdersByUserId(id);
    }
}
