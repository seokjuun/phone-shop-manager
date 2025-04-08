package me.hongseokjun.myapp.auth.dao;

import me.hongseokjun.myapp.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {
    UserDto login(String userEmail);
}
