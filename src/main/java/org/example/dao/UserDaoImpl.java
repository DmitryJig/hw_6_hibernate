package org.example.dao;

import lombok.RequiredArgsConstructor;
import org.example.models.Order;
import org.example.models.User;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final SessionFactoryBean sessionFactoryBean;


    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(Session session =sessionFactoryBean.getSession()){
            session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        try(Session session = sessionFactoryBean.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return Optional.of(user);
        }
    }

    @Override
    public List<Order> getOrdersByUserId(Long id) {
        List<Order> orders;
        try(Session session = sessionFactoryBean.getSession()) {
            session.beginTransaction();
            orders = new ArrayList<>(session.get(User.class, id).getOrders());
            session.getTransaction().commit();
        }
        return orders;
    }
}
