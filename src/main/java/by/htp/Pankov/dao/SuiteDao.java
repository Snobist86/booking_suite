package by.htp.Pankov.dao;

import by.htp.Pankov.connection.ConnectionPool;
import by.htp.Pankov.dto.suite.VacantSuiteSearchDto;
import by.htp.Pankov.entity.Suite;
import by.htp.Pankov.entity.SuiteCategory;
import by.htp.Pankov.entity.SuiteSize;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SuiteDao {

    private static final SuiteDao INSTANCE = new SuiteDao();

    private static final String SAVE = "INSERT INTO hotel_booking.suite " +
            "(number, suite_size_id, suite_category_id, price, floor) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SEARCH_VACANT_SUITE = "SELECT" +
            "  s.id," +
            "  s.number," +
            "  s.suite_size_id," +
            "  sz.name AS size_name," +
            "  sz.comment AS size_comment," +
            "  s.suite_category_id," +
            "  c.name AS category_name," +
            "  c.comment AS category_comment," +
            "  s.price " +
            "FROM hotel_booking.suite s" +
            "  INNER JOIN hotel_booking.suite_size sz" +
            "    ON s.suite_size_id = sz.id" +
            "  INNER JOIN hotel_booking.suite_category c" +
            "    ON s.suite_category_id = c.id " +
            "WHERE (? IS NULL OR s.suite_size_id = ?) AND (? IS NULL OR s.suite_category_id = ?)" +
            "      AND (s.id NOT IN (SELECT o.suite_id" +
            "                        FROM hotel_booking.order o" +
            "                          INNER JOIN hotel_booking.preview_order po" +
            "                            ON po.id = o.preview_order_id" +
            "                        WHERE po.check_in_date BETWEEN ? AND ?))" +
            "      AND (s.id NOT IN (SELECT o.suite_id" +
            "                        FROM hotel_booking.order o" +
            "                          INNER JOIN hotel_booking.preview_order po" +
            "                            ON po.id = o.preview_order_id" +
            "                        WHERE po.check_out_date BETWEEN ? AND ?))";

    private static final String FIND_SUITE_BY_NUMBER ="SELECT" +
            "  s.id," +
            "  s.number," +
            "  s.suite_size_id," +
            "  sz.name AS size_name," +
            "  s.suite_category_id," +
            "  c.name AS category_name," +
            "  s.price," +
            "  s.floor " +
            "FROM hotel_booking.suite s" +
            "  INNER JOIN hotel_booking.suite_size sz" +
            "  ON sz.id = s.suite_size_id" +
            "  INNER JOIN hotel_booking.suite_category c" +
            "  ON c.id = s.suite_category_id " +
            "WHERE number = ?";

    public void save(Suite suite) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, suite.getNumber());
            preparedStatement.setLong(2, suite.getSuiteSize().getId());
            preparedStatement.setLong(3, suite.getSuiteCategory().getId());
            preparedStatement.setInt(4, suite.getPrice());
            preparedStatement.setInt(5, suite.getFloor());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                suite.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<Suite> searchVacantSuites(VacantSuiteSearchDto vacantSuiteSearchDto) {
        Set<Suite> vacantSuites = new HashSet<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_VACANT_SUITE)) {

            preparedStatement.setObject(1, vacantSuiteSearchDto.getSuiteSizeId(), Types.BIGINT);
            preparedStatement.setObject(2, vacantSuiteSearchDto.getSuiteSizeId(), Types.BIGINT);
            preparedStatement.setObject(3, vacantSuiteSearchDto.getSuiteCategoryId(), Types.BIGINT);
            preparedStatement.setObject(4, vacantSuiteSearchDto.getSuiteCategoryId(), Types.BIGINT);
            preparedStatement.setDate(5, Date.valueOf(vacantSuiteSearchDto.getCheckInDate()));
            preparedStatement.setDate(6, Date.valueOf(vacantSuiteSearchDto.getCheckOutDate()));
            preparedStatement.setDate(7, Date.valueOf(vacantSuiteSearchDto.getCheckInDate()));
            preparedStatement.setDate(8, Date.valueOf(vacantSuiteSearchDto.getCheckOutDate()));
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Suite suite = getSuiteForSearch(resultSet);
                vacantSuites.add(suite);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vacantSuites;
    }

    public List<Suite> searchSuitesForOrder(VacantSuiteSearchDto vacantSuiteSearchDto) {
        List<Suite> vacantSuites = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_VACANT_SUITE)) {

            preparedStatement.setObject(1, vacantSuiteSearchDto.getSuiteSizeId(), Types.BIGINT);
            preparedStatement.setObject(2, vacantSuiteSearchDto.getSuiteSizeId(), Types.BIGINT);
            preparedStatement.setObject(3, vacantSuiteSearchDto.getSuiteCategoryId(), Types.BIGINT);
            preparedStatement.setObject(4, vacantSuiteSearchDto.getSuiteCategoryId(), Types.BIGINT);
            preparedStatement.setDate(5, Date.valueOf(vacantSuiteSearchDto.getCheckInDate()));
            preparedStatement.setDate(6, Date.valueOf(vacantSuiteSearchDto.getCheckOutDate()));
            preparedStatement.setDate(7, Date.valueOf(vacantSuiteSearchDto.getCheckInDate()));
            preparedStatement.setDate(8, Date.valueOf(vacantSuiteSearchDto.getCheckOutDate()));
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Suite suite = getSuiteForOrder(resultSet);
                vacantSuites.add(suite);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vacantSuites;
    }

//    public Optional<Suite> findByNumber(Integer number) {
//        Optional<Suite> suite = Optional.empty();
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_SUITE_BY_NUMBER)) {
//            preparedStatement.setLong(1, number);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                suite = Optional.of(getSuite(resultSet));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return suite;
//    }

    private Suite getSuiteForSearch(ResultSet resultSet) throws SQLException {
        return Suite.builder()
                .suiteSize(SuiteSize.builder()
                        .id(resultSet.getLong("suite_size_id"))
                        .name(resultSet.getString("size_name"))
                        .comment(resultSet.getString("size_comment"))
                        .build())
                .suiteCategory(SuiteCategory.builder()
                        .id(resultSet.getLong("suite_category_id"))
                        .name(resultSet.getString("category_name"))
                        .comment(resultSet.getString("category_comment"))
                        .build())
                .price(resultSet.getInt("price"))
                .build();
    }

    private Suite getSuiteForOrder(ResultSet resultSet) throws SQLException {
        return Suite.builder()
                .id(resultSet.getLong("id"))
                .number(resultSet.getInt("number"))
                .build();
    }


//    private Suite getSuite(ResultSet resultSet) throws SQLException {
//        return Suite.builder()
//                .id(resultSet.getLong("id"))
//                .number(resultSet.getInt("number"))
//                .suiteSize(SuiteSize.builder()
//                        .id(resultSet.getLong("suite_size_id"))
//                        .name(resultSet.getString("size_name"))
//                        .comment(resultSet.getString("size_comment"))
//                        .build())
//                .suiteCategory(SuiteCategory.builder()
//                        .id(resultSet.getLong("suite_category_id"))
//                        .name(resultSet.getString("category_name"))
//                        .comment(resultSet.getString("category_comment"))
//                        .build())
//                .price(resultSet.getInt("price"))
//                .floor(resultSet.getInt("floor"))
//                .build();
//    }

    public static SuiteDao getInstance() {
        return INSTANCE;
    }
}
