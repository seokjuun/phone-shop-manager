package me.hongseokjun.myapp.user.service;

import me.hongseokjun.myapp.user.dto.UserDto;
import me.hongseokjun.myapp.user.dto.UserResultDto;

public interface UserService {
    UserResultDto registerUser(UserDto userDto);
}
