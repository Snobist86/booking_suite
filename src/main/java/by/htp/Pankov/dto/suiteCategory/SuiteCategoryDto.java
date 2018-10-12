package by.htp.Pankov.dto.suiteCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuiteCategoryDto {

    private String name;
    private String comment;
}
