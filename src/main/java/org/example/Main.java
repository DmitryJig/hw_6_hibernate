package org.example;

import lombok.RequiredArgsConstructor;
import org.example.configs.AppConfig;
import org.example.dao.SessionFactoryBean;
import org.example.services.OrderService;
import org.example.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 1. В базе данных необходимо реализовать возможность хранить информацию о покупателях (id, имя) и товарах (id, название, стоимость). У каждого покупателя свой набор купленных товаров;
 * 2. Для обеих сущностей создаете Dao классы. Работу с SessionFactory выносите во вспомогательный класс;
 * 3. * Создаете сервис, позволяющий по id покупателя узнать список купленных им товаров, и по id товара узнавать список покупателей этого товара;
 * 4.** Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом.
 */
@RequiredArgsConstructor
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SessionFactoryBean sessionFactoryBean = context.getBean("sessionFactoryBean", SessionFactoryBean.class);
        UserService userService = context.getBean("userService", UserService.class);
        OrderService orderService = context.getBean("orderService", OrderService.class);

        // создаем таблицы и заполняем их из скрипта
        new FullDatabaseApp(sessionFactoryBean).forcePrepareData();

        // Определяем по ID покупателя список продуктов, которые он купил
        System.out.println(orderService.getProductsByUserId(2L));

        // Определяем по ID продукта список покупателей которые купили этот товар
        System.out.println(orderService.getUsersByProductId(1L));

        // Определяем цену товара по паре покупатель-товар (можно было в параметры для надежности добавить дату покупки)
        System.out.println(orderService.getPriceByUserAndProduct(2L, 1L));

//        System.out.println(userService.findUserById(1l).get()); // находим юзера по id
//        System.out.println(orderService.findAll());
//        System.out.println(userService.getOrdersByUserId(2l));

        context.close();
    }
}