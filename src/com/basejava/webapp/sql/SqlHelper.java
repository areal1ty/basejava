package com.basejava.webapp.sql;

import com.basejava.webapp.exception.StorageException;

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

    public <T> T transactionExecute(SqlTransaction<T> executor) {
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                connection.setAutoCommit(false);
                T result = executor.execute(connection);
                connection.commit();
                return result;
            } catch (SQLException e) {
                connection.rollback();
                throw ExceptionManager.convertException(e);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public interface SqlExecutor<T> {
        T execute(PreparedStatement preparedStatement) throws SQLException;
    }

    public interface SqlTransaction<T> {
        T execute(Connection connection) throws SQLException;
    }

}

