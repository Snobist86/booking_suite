package by.htp.Pankov.dao;

import by.htp.Pankov.connection.ConnectionPool;
import by.htp.Pankov.entity.SuiteCategory;
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
public final class SuiteCategoryDao {

    private static final SuiteCategoryDao INSTANCE = new SuiteCategoryDao();

    private static final String SAVE = "INSERT INTO hotel_booking.suite_category (name, comment) VALUES (?,?)";

    private static final String DELETE = "DELETE FROM hotel_booking.suite_category WHERE id = ?";

    private static final String FIND_ALL =
            "SELECT id, name, comment FROM hotel_booking.suite_category";

    private static final String FIND_CATEGORY_BY_ID =
            "SELECT id, name, comment FROM hotel_booking.suite_category " +
                    "WHERE id = ?";


    public SuiteCategory save(SuiteCategory suiteCategory) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, suiteCategory.getName());
            preparedStatement.setString(2,suiteCategory.getComment());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                suiteCategory.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suiteCategory;
    }

    public List<SuiteCategory> findAllCategory() {
        List<SuiteCategory> categories = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                SuiteCategory suiteCategory = SuiteCategory.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .comment(resultSet.getString("comment"))
                        .build();
                categories.add(suiteCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public Optional<SuiteCategory> findById(Long id) {
        Optional<SuiteCategory> category = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CATEGORY_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = Optional.of(getCategoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    private SuiteCategory getCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        return SuiteCategory.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .comment(resultSet.getString("comment"))
                .build();
    }

    public static SuiteCategoryDao getInstance(){ return INSTANCE;}


}
