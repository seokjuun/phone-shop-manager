package com.mycom.myapp.service;

import com.mycom.myapp.dto.EmployeeDto;
import com.mycom.myapp.dto.EmployeeRequestDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto register(EmployeeRequestDto requestDto);
    List<EmployeeDto> findAll();
    EmployeeDto findById(Long id);
    void deleteById(Long id);
}