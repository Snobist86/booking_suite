package by.htp.Pankov.dto.suite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacantSuiteSearchDto {

    private String suiteSizeId;
    private String suiteCategoryId;
    private String checkInDate;
    private String checkOutDate;
}
