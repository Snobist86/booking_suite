package by.htp.Pankov.service;

import by.htp.Pankov.dao.OrderDao;
import by.htp.Pankov.dto.order.OrderCreateDto;
import by.htp.Pankov.entity.Order;
import by.htp.Pankov.entity.PreviewOrder;
import by.htp.Pankov.entity.Suite;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderService {

    private static final OrderService INSTANCE = new OrderService();

    public void save(OrderCreateDto orderCreateDto) {
        OrderDao.getInstance().save(
                Order.builder()
                        .suite(Suite.builder()
                                .id(Long.valueOf(orderCreateDto.getSuiteId()))
                                .build())
                        .previewOrder(PreviewOrder.builder()
                                .id(Long.valueOf(orderCreateDto.getPreviewOrderId()))
                                .build())                        
                        .build());
    }

    public void deleteByPreviewOrderId(String previewOrderId) {
        OrderDao.getInstance().deleteByPreviewOrderId(previewOrderId);
    }

//    public List<Order> findAll() {
//        return OrderDao.getInstance().findAllCategory();
//    }
//
//    public void save(SuiteCategoryDto suiteCategoryDto) {
//        SuiteCategory suiteCategory = SuiteCategory.builder()
//                .name(suiteCategoryDto.getName())
//                .comment(suiteCategoryDto.getComment())
//                .build();
//        SuiteCategoryDao.getInstance().save(suiteCategory);
//    }


    public static OrderService getInstance() {
        return INSTANCE;
    }
}
