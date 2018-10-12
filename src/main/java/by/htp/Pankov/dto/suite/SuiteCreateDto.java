package by.htp.Pankov.dto.suite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuiteCreateDto {

    private String number;
    private String suiteSizeId;
    private String suiteCategoryId;
    private String price;
    private String floor;
}
