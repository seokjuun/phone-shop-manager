package com.mycom.myapp.service;

import com.mycom.myapp.dto.SaleTopEmployeeDto;
import com.mycom.myapp.dto.SalesStatDto;

import java.util.List;

public interface StatService {
    SalesStatDto getMySalesStat(Long employeeId);
    SalesStatDto getTotalSalesStat();
    Long countLowStockDevices();
    Long countNewCustomersThisMonth();
    List<SaleTopEmployeeDto> findTop3EmployeesThisMonth();
}