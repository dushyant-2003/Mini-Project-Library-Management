package com.library.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.library.util.LoggingUtil;

public abstract class GenericDAO<T> {

    private static final Logger logger = LoggingUtil.getLogger(GenericDAO.class);
    private Connection connection;

    public GenericDAO() {
        try {
            this.connection = DBConnectionManager.getConnection();
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error initializing database connection", e);
            e.printStackTrace();
        }
    }

    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    public T executeGetQuery(String query) throws SQLException, ClassNotFoundException {
        T entity = null;
        logger.log(Level.INFO, "Executing query: {0}", query);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
                logger.log(Level.INFO, "Entity retrieved from query.");
            } else {
                logger.log(Level.INFO, "No records found for query: {0}", query);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing query: {0}", query);
            throw e;
        }
        return entity;
    }

    public List<T> executeGetAllQuery(String query) throws SQLException, ClassNotFoundException {
        List<T> entities = new ArrayList<>();
        logger.log(Level.INFO, "Executing query to retrieve all records: {0}", query);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }

            logger.log(Level.INFO, "{0} records retrieved.", entities.size());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing query: {0}", query);
            throw e;
        }
        return entities;
    }

    public boolean executeUpdateQuery(String query) throws SQLException, ClassNotFoundException {
        logger.log(Level.INFO, "Executing update query: {0}", query);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            boolean result = preparedStatement.executeUpdate() > 0;
            logger.log(Level.INFO, "Update query executed successfully, result: {0}", result);
            return result;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing update query: {0}", query);
            throw e;
        }
    }
}
