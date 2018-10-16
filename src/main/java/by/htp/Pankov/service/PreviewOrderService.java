package by.htp.Pankov.service;

import by.htp.Pankov.dao.PreviewOrderDao;
import by.htp.Pankov.dto.previewOrder.AddPreviewOrderDto;
import by.htp.Pankov.dto.previewOrder.ChangeStatusPreviewOrderDto;
import by.htp.Pankov.dto.previewOrder.FindPreviewOrderDto;
import by.htp.Pankov.dto.previewOrder.SearchPreviewOrderDto;
import by.htp.Pankov.dto.user.UserDto;
import by.htp.Pankov.entity.OrderStatus;
import by.htp.Pankov.entity.PreviewOrder;
import by.htp.Pankov.entity.SuiteCategory;
import by.htp.Pankov.entity.SuiteSize;
import by.htp.Pankov.entity.User;
import by.htp.Pankov.validator.LocalDateFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PreviewOrderService {

    public static int countApartmentOnPage=5;

    private static final PreviewOrderService INSTANCE = new PreviewOrderService();

    public void changeStatus (ChangeStatusPreviewOrderDto dto){
        PreviewOrderDao.getInstance().changeStatus(dto);
    }

    public void save(AddPreviewOrderDto addPreviewOrderDto) {
        PreviewOrderDao.getInstance().save(
                PreviewOrder.builder()
                        .user(User.builder()
                                .id((Long.valueOf(addPreviewOrderDto.getUserId())))
                                .build())
                        .suiteSize(SuiteSize.builder()
                                .id(Long.valueOf(addPreviewOrderDto.getSuiteSizeId()))
                                .build())
                        .suiteCategory(SuiteCategory.builder()
                                .id(Long.valueOf(addPreviewOrderDto.getSuiteCategoryId()))
                                .build())
                        .checkIn(LocalDateFormat.format(addPreviewOrderDto.getCheckIn()))
                        .checkOut(LocalDateFormat.format(addPreviewOrderDto.getCheckOut()))
                        .bookingDate(LocalDate.now())
                        .totalPrice(getTotalPrice(addPreviewOrderDto))
                        .comment(addPreviewOrderDto.getComment())
                        .build());
    }

    public void delete (String previewOrderId){
        PreviewOrderDao.getInstance().delete(previewOrderId);
    }

    public List<FindPreviewOrderDto> search(String previewOrderId) {
        return PreviewOrderDao.getInstance().findPreviewOrderByStatusOrderId(previewOrderId).stream()
                .map(it -> new FindPreviewOrderDto(String.valueOf(it.getId()),
                        String.valueOf(it.getUser().getId()),
                        String.valueOf(it.getUser().getLogin()),
                        String.valueOf(it.getSuiteSize().getId()),
                        String.valueOf(it.getSuiteSize().getName()),
                        String.valueOf(it.getSuiteCategory().getId()),
                        String.valueOf(it.getSuiteCategory().getName()),
                        String.valueOf(it.getOrderStatus().getId()),
                        String.valueOf(it.getOrderStatus().getTitle()),
                        String.valueOf(it.getCheckIn()),
                        String.valueOf(it.getCheckOut()),
                        String.valueOf(it.getBookingDate()),
                        String.valueOf(it.getTotalPrice()),
                        String.valueOf(it.getComment())))
                .collect(Collectors.toList());
    }

    public List<FindPreviewOrderDto> findAllPreviewOrderOfUser(User user) {
        return PreviewOrderDao.getInstance().findPreviewOrderByUserId(user).stream()
                .map(it -> new FindPreviewOrderDto(String.valueOf(it.getId()),
                        String.valueOf(it.getUser().getId()),
                        String.valueOf(it.getUser().getLogin()),
                        String.valueOf(it.getSuiteSize().getId()),
                        String.valueOf(it.getSuiteSize().getName()),
                        String.valueOf(it.getSuiteCategory().getId()),
                        String.valueOf(it.getSuiteCategory().getName()),
                        String.valueOf(it.getOrderStatus().getId()),
                        String.valueOf(it.getOrderStatus().getTitle()),
                        String.valueOf(it.getCheckIn()),
                        String.valueOf(it.getCheckOut()),
                        String.valueOf(it.getBookingDate()),
                        String.valueOf(it.getTotalPrice()),
                        String.valueOf(it.getComment())))
                .collect(Collectors.toList());
    }

    private int getTotalPrice(AddPreviewOrderDto addPreviewOrderDto) {
        Integer unitPrice = Integer.parseInt(addPreviewOrderDto.getPrice());
        LocalDate dateIn = LocalDateFormat.format(addPreviewOrderDto.getCheckIn());
        LocalDate dateOut = LocalDateFormat.format(addPreviewOrderDto.getCheckOut());
        long numberOfDays = ChronoUnit.DAYS.between(dateIn, dateOut);

        return (int) (unitPrice * numberOfDays);
    }

    public static PreviewOrderService getInstance() {
        return INSTANCE;
    }
}
