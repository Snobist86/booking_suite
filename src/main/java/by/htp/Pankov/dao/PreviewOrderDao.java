package by.htp.Pankov.dao;

import by.htp.Pankov.connection.ConnectionPool;
import by.htp.Pankov.dto.previewOrder.SearchPreviewOrderDto;
import by.htp.Pankov.entity.OrderStatus;
import by.htp.Pankov.entity.PreviewOrder;
import by.htp.Pankov.entity.SuiteCategory;
import by.htp.Pankov.entity.SuiteSize;
import by.htp.Pankov.entity.User;
import by.htp.Pankov.validator.LocalDateFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PreviewOrderDao {

    private static final PreviewOrderDao INSTANCE = new PreviewOrderDao();

    private static final String SAVE = "INSERT " +
            "INTO hotel_booking.preview_order (user_id, suite_size_id, suite_category_id, " +
            "                                  check_in_date, check_out_date, booking_date, total_price, comment) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SEARCH_PREVIEW_ORDER_BY_STATUS_ID = "SELECT" +
            "  po.id," +
            "  po.user_id," +
            "  u.login," +
            "  po.suite_size_id," +
            "  ss.name AS size_name," +
            "  po.suite_category_id," +
            "  sc.name AS category_name," +
            "  po.order_status_id," +
            "  os.title," +
            "  po.check_in_date," +
            "  po.check_out_date," +
            "  po.booking_date," +
            "  po.comment," +
            "  po.total_price " +
            "FROM hotel_booking.preview_order po" +
            "  INNER JOIN hotel_booking.application_user u" +
            "    ON po.user_id = u.id" +
            "  INNER JOIN hotel_booking.order_status os" +
            "    ON po.order_status_id = os.id" +
            "  INNER JOIN hotel_booking.suite_size ss" +
            "  ON po.suite_size_id = ss.id" +
            "  INNER JOIN hotel_booking.suite_category sc" +
            "  ON po.suite_category_id = sc.id " +
            "WHERE order_status_id = ?";

    public void save(PreviewOrder previewOrder) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, previewOrder.getUser().getId());
            preparedStatement.setLong(2, previewOrder.getSuiteSize().getId());
            preparedStatement.setLong(3, previewOrder.getSuiteCategory().getId());
            preparedStatement.setDate(4, Date.valueOf(previewOrder.getCheckIn()));
            preparedStatement.setDate(5, Date.valueOf(previewOrder.getCheckOut()));
            preparedStatement.setDate(6, Date.valueOf(previewOrder.getBookingDate()));
            preparedStatement.setInt(7, previewOrder.getTotalPrice());
            preparedStatement.setString(8, previewOrder.getComment());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                previewOrder.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PreviewOrder> findPreviewOrderByStatusOrderId(SearchPreviewOrderDto dto) {
        List<PreviewOrder> previewOrders = new LinkedList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PREVIEW_ORDER_BY_STATUS_ID)) {
            preparedStatement.setObject(1, dto.getId(), Types.BIGINT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PreviewOrder previewOrder = getPreviewOrderFromResultSet(resultSet);
                previewOrders.add(previewOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return previewOrders;
    }

    private PreviewOrder getPreviewOrderFromResultSet(ResultSet resultSet) throws SQLException {
        return PreviewOrder.builder()
                .id(resultSet.getLong("id"))
                .user(User.builder()
                        .id(resultSet.getLong("user_id"))
                        .login(resultSet.getString("login"))
                        .build())
                .suiteSize(SuiteSize.builder()
                        .id(resultSet.getLong("suite_size_id"))
                        .name(resultSet.getString("size_name"))
                        .build())
                .suiteCategory(SuiteCategory.builder()
                        .id(resultSet.getLong("suite_category_id"))
                        .name(resultSet.getString("category_name"))
                        .build())
                .orderStatus(OrderStatus.builder()
                        .id(resultSet.getLong("order_status_id"))
                        .title(resultSet.getString("title"))
                        .build())
                .checkIn(LocalDateFormat.format(resultSet.getString("check_in_date")))
                .checkOut(LocalDateFormat.format(resultSet.getString("check_out_date")))
                .bookingDate(LocalDateFormat.format(resultSet.getString("booking_date")))
                .totalPrice(resultSet.getInt("total_price"))
                .comment(resultSet.getString("comment"))
                .build();
    }

    public static PreviewOrderDao getInstance() {
        return INSTANCE;
    }
}
