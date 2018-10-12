package by.htp.Pankov.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Suite {

    private Long id;
    private Integer number;
    private SuiteSize suiteSize;
    private SuiteCategory suiteCategory;
    private Integer price;
    private Integer floor;
}
