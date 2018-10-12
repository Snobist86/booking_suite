package by.htp.Pankov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String login;
    private String password;
    private String eMail;
    private Integer roleId;

    public User(Long id, String login, Integer roleId) {
        this.id = id;
        this.login = login;
        this.roleId = roleId;
    }
}
