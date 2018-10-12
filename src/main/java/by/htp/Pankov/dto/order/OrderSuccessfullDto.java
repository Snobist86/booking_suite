package by.htp.Pankov.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderSuccessfullDto {

    private String id;
    private String suiteSize;
    private String suiteCategory;
    private String checkIn;
    private String checkOut;
    private String totalPrice;
}
