package com.mycom.myapp.controller;


import com.mycom.myapp.dto.SaleTopEmployeeDto;
import com.mycom.myapp.dto.SalesStatDto;
import com.mycom.myapp.dto.StatSummaryDto;
import com.mycom.myapp.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;




}