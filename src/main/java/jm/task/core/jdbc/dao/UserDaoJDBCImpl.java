package jm.task.core.jdbc.dao;
import java.sql.*;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age INT)");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("Drop table if exists my_db.users");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        String str = "INSERT INTO my_db.users(name, last_name, age) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            String str = "DELETE FROM my_db.users where id";
            statement.executeUpdate(str);
            System.out.println("User удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public void cleanUsersTable() {
            String str = "DELETE FROM my_db.users";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(str);
                System.out.println("Таблица очищена");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Не удалось очистить");
            }
        }
    }


