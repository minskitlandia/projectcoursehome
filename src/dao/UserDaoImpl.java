package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements BaseDao<User>, AuthDao {
    private static final String INSERT_USER = "INSERT INTO User VALUES (?,?,?,?);";
    private static final String UPDATE_USER = "UPDATE User SET login = ?, password = ?, role_id=? where id = ?;";
    private static final String SELECT_USER_ID = " SELECT * FROM User where id = ?;";
    private static final String DELETE_USER_ID = " DELETE FROM User where id = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM User";
    private static final String VALIDATE_USER = "SELECT * FROM User WHERE login=? AND password=?";

    @Override
    public void insert(User entity) {
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {

            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getLogin());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setInt(4, entity.getRole_id());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            printSQLException(e);
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(User entity) {
        boolean flagUpdate = false;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setInt(3, entity.getRole_id());
            preparedStatement.setInt(4, entity.getId());
            System.out.println(preparedStatement);
            flagUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
//            printSQLException(e);
            e.printStackTrace();
        }
        return flagUpdate;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            printSQLException(e);
            e.printStackTrace();
        }
    }

    @Override
    public User read(int id) {
        User user = null;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ID)) {

            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                int role_id = resultSet.getInt(4);
                user = new User(id, login, password, role_id);
            }
        } catch (SQLException e) {
//            printSQLException(e);
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> read() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = DBConnection.INSTANCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                int role_id = resultSet.getInt(4);
                User user = new User(id, login, password, role_id);
                users.add(user);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
//            printSQLException(e);
            e.printStackTrace();
        }
        return users;
    }

    private void printSQLException(SQLException exception) {
        for (Throwable e : exception) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Messsage: " + e.getMessage());
                Throwable throwable = exception.getCause();
                while (throwable != null) {
                    System.out.println("Cause: " + throwable);
                    throwable.getCause();
                }
            }
        }
    }

    @Override
    public User login(User user) {
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return User.builder()
                        .id(resultSet.getInt(1))
                        .login(resultSet.getString(2))
                        .role_id(resultSet.getInt(4))
                        .build();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User();
    }

    @Override
    public void signup(User user) {
        insert(user);
    }
}
