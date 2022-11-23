package org.example;

import lombok.RequiredArgsConstructor;
import org.example.dao.SessionFactoryBean;
import org.hibernate.Session;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FullDatabaseApp {
    private final SessionFactoryBean sessionFactoryBean;

    public void forcePrepareData() {
        try (Session session = sessionFactoryBean.getSession()) {
            String sql = Files.lines(Paths.get("full.sql")).collect(Collectors.joining(" "));
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
