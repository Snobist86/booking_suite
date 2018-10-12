package by.htp.Pankov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreviewOrder {

    Long id;
    User user;
    SuiteSize suiteSize;
    SuiteCategory suiteCategory;
    OrderStatus orderStatus;
    LocalDate checkIn;
    LocalDate checkOut;
    LocalDate bookingDate;
    Integer totalPrice;
    String comment;
}
