package me.hongseokjun.myapp.user.service;

import me.hongseokjun.myapp.user.dao.UserDao;
import me.hongseokjun.myapp.user.dto.UserDto;
import me.hongseokjun.myapp.user.dto.UserResultDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserResultDto registerUser(UserDto userDto) {
        UserResultDto userResultDto = new UserResultDto();
        if(userDao.registerUser(userDto) == 1) {
            userResultDto.setResult("success");
        } else {
            userResultDto.setResult("fail");
        }
        return userResultDto;
    }
}
