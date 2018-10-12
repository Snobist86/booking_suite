package by.htp.Pankov.dao;

import by.htp.Pankov.connection.ConnectionPool;
import by.htp.Pankov.entity.OrderStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderStatusDao {

    private static final OrderStatusDao INSTANCE = new OrderStatusDao();

    private static final String SAVE = "INSERT INTO hotel_booking.order_status (title) VALUES (?)";

    private static final String DELETE = "DELETE FROM hotel_booking.order_status WHERE id = ?";

    private static final String FIND_ALL =
            "SELECT id, title FROM hotel_booking.order_status";

    public List<OrderStatus> findAll() {
        List<OrderStatus> orderStatusList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                OrderStatus orderStatus = OrderStatus.builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .build();
                orderStatusList.add(orderStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderStatusList;
    }

    public static OrderStatusDao getInstance(){ return INSTANCE;}
}
