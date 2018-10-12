package by.htp.Pankov.service;

import by.htp.Pankov.dao.UserDao;
import by.htp.Pankov.dto.user.ChangeRoleUserDto;
import by.htp.Pankov.dto.user.FullInfoUserDto;
import by.htp.Pankov.dto.user.UserDto;
import by.htp.Pankov.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserService {

    private static final UserService INSTANCE = new UserService();

    public void save(FullInfoUserDto dto) {
        User user = User.builder()
                .login(dto.getLogin())
                .password(dto.getPassword())
                .eMail(dto.getEMail())
                .build();
        UserDao.getInstance().save(user);
    }

    public UserDto findByLogin(String login) {
        return UserDao.getInstance().findByLogin(login)
                .map(it -> UserDto.builder()
                        .id(it.getId())
                        .login(it.getLogin())
                        .build())
                .orElse(null);
    }

    public void changeRole(String login, String roleId) {
        UserDao.getInstance().findByLogin(login)
                .map(it -> ChangeRoleUserDto.builder()
                        .id(it.getId())
                        .login(it.getLogin())
                        .roleId(Integer.valueOf(roleId))
                        .build()).ifPresent(user -> UserDao.getInstance().changeRole(user));
    }

    public UserDto findById(Long sightId) {
        return UserDao.getInstance().findById(sightId)
                .map(it -> UserDto.builder()
                        .id(it.getId())
                        .login(it.getLogin())
                        .build())
                .orElse(null);
    }

    public static UserService getInstance() {

        return INSTANCE;
    }
}
