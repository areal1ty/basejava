package com.basejava.webapp.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String query) {
        execute(query, PreparedStatement::execute);
    }

    public <T> T execute(String query, SqlExecutor<T> executor) {
        try (Connection connection = connectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw ExceptionManager.convertException(e);
        }
    }

    public interface SqlExecutor<T> {
        T execute(PreparedStatement preparedStatement) throws SQLException;
    }

}

