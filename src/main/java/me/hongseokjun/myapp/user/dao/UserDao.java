package me.hongseokjun.myapp.user.dao;

import me.hongseokjun.myapp.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    int registerUser(UserDto userDto);
}
