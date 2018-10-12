package by.htp.Pankov.dao;


import by.htp.Pankov.entity.Order;

public final class OrderDao {

    private static final OrderDao INSTANCE = new OrderDao();



    public static OrderDao getInstance() {
        return INSTANCE;
    }

}
