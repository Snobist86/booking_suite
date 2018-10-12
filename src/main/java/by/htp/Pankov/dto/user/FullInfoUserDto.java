package by.htp.Pankov.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullInfoUserDto {

    private Long id;
    private String login;
    private String password;
    private String eMail;
}
