package com.mycom.myapp.service;

import com.mycom.myapp.dto.EmployeeDto;
import com.mycom.myapp.dto.EmployeeResultDto;

import java.util.List;

public interface EmployeeService {
    EmployeeResultDto register(EmployeeDto requestDto);
    List<EmployeeDto> findAll();
    EmployeeDto findById(Long id);
    void deleteById(Long id);

    EmployeeResultDto login(String email, String password);
}