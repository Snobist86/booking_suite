package by.htp.Pankov.service;

import by.htp.Pankov.entity.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderService {

    private static final OrderService INSTANCE = new OrderService();

//    public List<Order> findAll() {
//        return OrderDao.getInstance().findAllCategory();
//    }

//    public void save(SuiteCategoryDto suiteCategoryDto) {
//        SuiteCategory suiteCategory = SuiteCategory.builder()
//                .name(suiteCategoryDto.getName())
//                .comment(suiteCategoryDto.getComment())
//                .build();
//        SuiteCategoryDao.getInstance().save(suiteCategory);
//    }

    public static OrderService getInstance () {
        return INSTANCE;
    }
}
