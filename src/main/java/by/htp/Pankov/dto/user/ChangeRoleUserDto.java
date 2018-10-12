package by.htp.Pankov.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeRoleUserDto {

    private Long id;
    private String login;
    private Integer roleId;
}
