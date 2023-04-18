package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    private Connection connection;

    public UserDaoJdbcImpl() {
        this.connection = Util.getConnection();
    }

    public void createUsersTable() {
        String sql= "create table if not exists users(" +
                "id serial primary key," +
                "name varchar not null," +
                "last_name varchar not null," +
                "age smallint not null);";
        try(Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Successfully created table!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql ="drop table if exists users;";
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Successfully Drop!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users(name,last_name,age)" +
                "values(?,?,?);";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
             preparedStatement.execute();
            System.out.println("Successfully saved!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        String sql ="delete from users where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            System.out.println("Successfully removed!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql ="truncate table users;";
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Successfully cleaned!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}