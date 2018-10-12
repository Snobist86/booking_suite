package by.htp.Pankov.dao;

import by.htp.Pankov.connection.ConnectionPool;
import by.htp.Pankov.dto.user.ChangeRoleUserDto;
import by.htp.Pankov.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE = "INSERT INTO hotel_booking.application_user (login, password, e_mail) VALUES (?, ?, ?)";

    private static final String DELETE = "DELETE FROM hotel_booking.application_user WHERE id = ?";

    private static final String FIND_ALL =
            "SELECT " +
                    "id, " +
                    "login, " +
                    "password, " +
                    "e_mail, " +
                    "role_id " +
                    "FROM hotel_booking.application_user u";

    private static final String FIND_USER_BY_LOGIN = FIND_ALL + " WHERE u.login = ?";

    private static final String FIND_USER_BY_ID = FIND_ALL + " WHERE u.id = ?";

    private static final String CHANGE_ROLE =
            "UPDATE hotel_booking.application_user SET role_id = ? WHERE id = ?";

    public void save(User user) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEMail());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<User> findByLogin(String login) {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(User.builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .eMail(resultSet.getString("e_mail"))
                        .roleId(resultSet.getInt("role_id"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Optional<User> findById(Long id) {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(User.builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .eMail(resultSet.getString("eMail"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void changeRole(ChangeRoleUserDto user) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ROLE)) {
            preparedStatement.setLong(1, user.getRoleId());
            preparedStatement.setLong(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .build();
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void delete(Long id) {
        Connection connection = null;
        PreparedStatement countryStatement = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);

            countryStatement = connection.prepareStatement(DELETE);
            countryStatement.setLong(1, id);
            countryStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (countryStatement != null) {
                    countryStatement.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
