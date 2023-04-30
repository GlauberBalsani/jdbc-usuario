package org.balsani.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.balsani.exceptions.DbException;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            return createDataSource().getConnection();
        } catch (SQLException e) {
            throw new DbException("Falha ao conectar ao banco de dados!!");
        }
    }

    private HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/usuarios");
        config.setUsername("postgres");
        config.setPassword("123");
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }
}
