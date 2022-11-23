package org.example.dao;

import lombok.RequiredArgsConstructor;
import org.example.models.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao{
    private final SessionFactoryBean sessionFactoryBean;

}
