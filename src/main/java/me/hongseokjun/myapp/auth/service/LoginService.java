package me.hongseokjun.myapp.auth.service;

import me.hongseokjun.myapp.user.dto.UserDto;

import java.util.Optional;

public interface LoginService {
    Optional<UserDto> login(UserDto userDto);

}
