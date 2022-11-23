package org.example.dao;

import lombok.RequiredArgsConstructor;
import org.example.models.Order;
import org.example.models.Product;
import org.example.models.User;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {
    private final SessionFactoryBean sessionFactoryBean;


    @Override
    public List<Order> findAll() {
        List<Order> orders = null;
        try (Session session = sessionFactoryBean.getSession()) {
            session.beginTransaction();
            orders = session.createQuery("from Order")
                    .getResultList();
            session.getTransaction().commit();
        }
        return orders;
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        try (Session session = sessionFactoryBean.getSession()) {
            session.beginTransaction();
            Order order = session.get(Order.class, id);
            session.getTransaction().commit();
            return Optional.of(order);
        }
    }

    /**
     * находим продукты, которые покупал полупатель по id покупателя
     * @param userId id покупателя
     * @return список продуктов которые покупал юзер
     */
    @Override
    public List<Product> getProductsByUserId(Long userId) {
        try(Session session = sessionFactoryBean.getSession()){
            session.beginTransaction();
            List<Product> products = session.createQuery("select o.product from Order o where o.user.id =:param")
                    .setParameter("param", userId)
                    .getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    /**
     * находим покупателей, которые приобрели товар по id товара
     * @param productId id продукта
     * @return список покупателей, которые купили продукт c указанным id
     */
    @Override
    public List<User> getUsersByProductId(Long productId) {
        try(Session session = sessionFactoryBean.getSession()){
            session.beginTransaction();
            List<User> users = session.createQuery("select o.user from Order o where o.product.id = :param")
                    .setParameter("param",productId)
                    .getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public Double getPriceByUserAndProduct(Long userId, Long productId) {
        try(Session session = sessionFactoryBean.getSession()){
            session.beginTransaction();
            Double priice = session.createQuery("select o from Order o where o.user.id = :userId and o.product.id = :productId", Order.class)
                    .setParameter("userId", userId)
                    .setParameter("productId", productId)
                    .getSingleResult().getCost();
            session.getTransaction().commit();
            return priice;
        }
    }
}
