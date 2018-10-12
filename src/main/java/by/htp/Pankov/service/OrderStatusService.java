package by.htp.Pankov.service;

import by.htp.Pankov.dao.OrderStatusDao;
import by.htp.Pankov.entity.OrderStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderStatusService {

    private static final OrderStatusService INSTANCE = new OrderStatusService();

    public List<OrderStatus> findAll() {
        return OrderStatusDao.getInstance().findAll();
    }

    public static OrderStatusService getInstance() {
        return INSTANCE;
    }
}
