package com.mycom.myapp.service;

import com.mycom.myapp.dto.SaleRequestDto;
import com.mycom.myapp.dto.SaleResponseDto;

public interface SaleService {
    SaleResponseDto calculateAndSaveSale(SaleRequestDto dto);
}