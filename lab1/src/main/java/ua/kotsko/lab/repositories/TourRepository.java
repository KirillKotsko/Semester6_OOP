package ua.kotsko.lab.repositories;

import ua.kotsko.lab.entities.TourEntity;
import ua.kotsko.lab.exceptions.HttpException;
import ua.kotsko.lab.Properties;

import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourRepository {
    public TourRepository() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(
                Properties.getInstance().getDatabaseUrl(),
                Properties.getInstance().getDatabaseUsername(),
                Properties.getInstance().getDatabasePassword());
             Statement statement = connection.createStatement()) {
            statement.execute("""
                    create table if not exists tour
                   (
                       id    bigint generated by default as identity primary key,
                       count bigint,
                       name  varchar(255),
                       price bigint
                   );
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TourEntity> findAll() {
        try (Connection connection = DriverManager.getConnection(
                Properties.getInstance().getDatabaseUrl(),
                Properties.getInstance().getDatabaseUsername(),
                Properties.getInstance().getDatabasePassword());
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tour ORDER BY id;");
            ArrayList<TourEntity> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(TourEntity.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .price(resultSet.getLong("price"))
                        .count(resultSet.getLong("count"))
                        .build());
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TourEntity findById(Long id) {
        try (Connection connection = DriverManager.getConnection(
                Properties.getInstance().getDatabaseUrl(),
                Properties.getInstance().getDatabaseUsername(),
                Properties.getInstance().getDatabasePassword());
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tour WHERE id = '%s';".formatted(id));
            if (!resultSet.next()) {
                throw new HttpException(HttpServletResponse.SC_NOT_FOUND);
            }
            return TourEntity.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getLong("price"))
                    .count(resultSet.getLong("count"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCount(Long id, Long nuwCount) {
        try (Connection connection = DriverManager.getConnection(
                Properties.getInstance().getDatabaseUrl(),
                Properties.getInstance().getDatabaseUsername(),
                Properties.getInstance().getDatabasePassword());
             Statement statement = connection.createStatement()) {
            statement.execute("UPDATE tour SET count = %s WHERE id = %s;"
                    .formatted(nuwCount, id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}