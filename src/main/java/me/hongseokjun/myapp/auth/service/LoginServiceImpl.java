package me.hongseokjun.myapp.auth.service;

import me.hongseokjun.myapp.auth.dao.LoginDao;
import me.hongseokjun.myapp.user.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    private final LoginDao loginDao;
    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Override
    public Optional<UserDto> login(UserDto userDto) {
        UserDto dto = loginDao.login(userDto.getEmail());
        if(dto != null && userDto.getPassword().equals(dto.getPassword())) {
            return Optional.of(dto);
        }
        return Optional.empty();
    }
}
