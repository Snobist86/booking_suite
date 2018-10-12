package by.htp.Pankov.dto.suite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindSuiteDto {

    private String suiteSizeId;
    private String suiteSize;
    private String suiteCategoryId;
    private String suiteCategory;
    private String price;
    private String commentSize;
    private String commentCategory;
}
