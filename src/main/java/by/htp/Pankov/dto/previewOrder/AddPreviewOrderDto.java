package by.htp.Pankov.dto.previewOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddPreviewOrderDto {

    private String userId;
    private String suiteSizeId;
    private String suiteCategoryId;
    private String checkIn;
    private String checkOut;
    private String price;
    private String comment;
}
