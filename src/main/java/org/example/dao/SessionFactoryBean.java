package org.example.dao;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Getter
public class SessionFactoryBean {

    SessionFactory factory;

    public Session getSession(){
        return factory.getCurrentSession();
    }

    @PostConstruct
    public void init() {
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @PreDestroy
    public void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}
