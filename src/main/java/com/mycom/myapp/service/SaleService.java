package com.mycom.myapp.service;

import com.mycom.myapp.dto.SaleRequestDto;
import com.mycom.myapp.dto.SaleResponseDto;
import com.mycom.myapp.dto.SaleTopEmployeeDto;
import com.mycom.myapp.dto.SalesStatDto;

import java.util.List;

public interface SaleService {
    SaleResponseDto calculateAndSaveSale(SaleRequestDto dto);


    SalesStatDto getSalesStat(Long employeeId);



}