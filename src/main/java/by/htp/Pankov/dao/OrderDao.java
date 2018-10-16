package by.htp.Pankov.dao;


import by.htp.Pankov.connection.ConnectionPool;
import by.htp.Pankov.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class OrderDao {

    private static final OrderDao INSTANCE = new OrderDao();

    private static final String DELETE_BY_PREVIEW_ORDER_ID = "DELETE FROM hotel_booking.order " +
            "WHERE preview_order_id = ?";

    private static final String SAVE = "INSERT INTO hotel_booking.order(suite_id, preview_order_id) VALUES (?, ?)";

    public void save(Order order) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, order.getSuite().getId());
            preparedStatement.setLong(2, order.getPreviewOrder().getId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByPreviewOrderId(String previewOrderId) {
        Connection connection = null;
        PreparedStatement orderStatement = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);

            orderStatement = connection.prepareStatement(DELETE_BY_PREVIEW_ORDER_ID);
            orderStatement.setLong(1, Long.valueOf(previewOrderId));
            orderStatement.executeUpdate();

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
                if (orderStatement != null) {
                    orderStatement.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }

}
