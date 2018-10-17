package by.htp.Pankov.dao;

import by.htp.Pankov.connection.ConnectionPool;
import by.htp.Pankov.entity.SuiteSize;
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
public final class SuiteSizeDao {

    private static final SuiteSizeDao INSTANCE = new SuiteSizeDao();

    private static final String SAVE = "INSERT INTO hotel_booking.suite_size (name, max_capacity, comment) VALUES (?,?,?)";

    private static final String DELETE = "DELETE FROM hotel_booking.suite_size WHERE id = ?";

    private static final String FIND_ALL =
            "SELECT id, name, comment, max_capacity FROM hotel_booking.suite_size";

    private static final String FIND_SIZE_BY_ID =
            "SELECT id, name, comment, max_capacity FROM hotel_booking.suite_size WHERE id = ?";

    public SuiteSize save(SuiteSize suiteSize) {
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, suiteSize.getName());
            preparedStatement.setInt(2,suiteSize.getMaxCapacity());
            preparedStatement.setString(3, suiteSize.getComment());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                suiteSize.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suiteSize;
    }

    public List<SuiteSize> findAllSize() {
        List<SuiteSize> sizes = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                SuiteSize size = getSizeFromResultSet(resultSet);
                sizes.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sizes;
    }

    public Optional<SuiteSize> findById(Long id) {
        Optional<SuiteSize> size = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_SIZE_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                size = Optional.of(getSizeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return size;
    }

    private SuiteSize getSizeFromResultSet(ResultSet resultSet) throws SQLException {
        return SuiteSize.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .comment(resultSet.getString("comment"))
                .maxCapacity(resultSet.getInt("max_capacity"))
                .build();
    }

    public static SuiteSizeDao getInstance() {
        return INSTANCE;
    }

}
