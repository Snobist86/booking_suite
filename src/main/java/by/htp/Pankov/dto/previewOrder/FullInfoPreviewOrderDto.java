package by.htp.Pankov.dto.previewOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullInfoPreviewOrderDto {

    private String id;
    private String userId;
    private String suiteSizeId;
    private String suiteCategoryId;
    private String orderStatusId;
    private String checkIn;
    private String checkOut;
    private String price;
    private String comment;
}
