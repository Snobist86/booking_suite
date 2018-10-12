package by.htp.Pankov.dto.previewOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindPreviewOrderDto {

    private String id;
    private String userId;
    private String userName;
    private String suiteSizeId;
    private String suiteSizeName;
    private String suiteCategoryId;
    private String suiteCategoryName;
    private String orderStatusId;
    private String orderStatusTitle;
    private String checkIn;
    private String checkOut;
    private String bookingDate;
    private String totalPrice;
    private String comment;
}
