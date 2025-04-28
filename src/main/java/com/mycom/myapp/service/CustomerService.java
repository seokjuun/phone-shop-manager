package com.mycom.myapp.service;

import com.mycom.myapp.dto.CustomerDto;
import com.mycom.myapp.dto.CustomerRequestDto;
import com.mycom.myapp.dto.SaleTopEmployeeDto;

import java.util.List;

public interface CustomerService {
    CustomerDto register(CustomerRequestDto dto);
    List<CustomerDto> findAll();
    CustomerDto findById(Long customerId);
    CustomerDto update(Long customerId, CustomerRequestDto dto);
    void deleteById(Long customerId);
}
