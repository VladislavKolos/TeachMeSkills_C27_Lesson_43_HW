package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.User;
import org.example.util.PostgresUtil;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Service for working with users
 * Provides methods for creating, getting, updating, and deleting users in a database.
 */
//@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final PostgresUtil postgresUtil;

    /**
     * Creates a new user in the database.
     * @param user user to add
     * @throws SQLException if an error occurs while working with the database
     */
    public void createUser(User user) throws SQLException {
        Connection connection = postgresUtil.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement preparedStatement = null;

        String sqlQuery = "INSERT INTO users (id, email, login) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            System.out.println("Exception!!!");

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            connection.setAutoCommit(true);
            connection.close();
        }
    }

    /**
     * Gets user from database.
     * @param id user ID
     * @return the user found or null if the user is not found
     * @throws SQLException if an error occurs while working with the database
     */
    public User getUser(int id) throws SQLException {
        Connection connection = postgresUtil.getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet preparedResultSet = null;

        String sqlQuery = "SELECT * FROM users WHERE id = ?";

        User user = null;

        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            preparedResultSet = preparedStatement.executeQuery();

            if (preparedResultSet.next()) {
                user = User.builder()
                        .id(preparedResultSet.getInt("id"))
                        .email(preparedResultSet.getString("email"))
                        .login(preparedResultSet.getString("login"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception!!!");

        } finally {
            if (preparedResultSet != null) {
                preparedResultSet.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return user;
    }

    /**
     * Changes the user login in the database.
     * @param id user ID
     * @param email user's email
     * @param newLogin new user login
     * @throws SQLException if an error occurs while working with the database
     */
    public void updateUser(int id, String email, String newLogin) throws SQLException {
        Connection connection = postgresUtil.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement preparedStatement = null;

        String sqlQuery = "UPDATE users SET login = ? WHERE id = ? AND email = ?";

        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, newLogin);
            preparedStatement.setInt(2, id);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            System.out.println("Exception!!!");

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            connection.setAutoCommit(true);
            connection.close();
        }
    }

    /**
     * Removes a user from the database by ID.
     * @param id user ID
     * @throws SQLException if an error occurs while working with the database
     */
    public void deleteUser(int id) throws SQLException {
        Connection connection = postgresUtil.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement preparedStatement = null;

        String sqlQuery = "DELETE FROM users WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            System.out.println("Exception!!!");

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            connection.setAutoCommit(true);
            connection.close();
        }
    }
}
