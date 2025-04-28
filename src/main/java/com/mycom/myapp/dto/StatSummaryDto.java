package com.mycom.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatSummaryDto {
    private SalesStatDto myStat; // 내 실적
    private SalesStatDto totalStat; // 전체 실적
    private Long lowStockCount; // 재고 부족
    private Long newCustomerCount; // 신규 고객 수
    private List<SaleTopEmployeeDto> top3Employees; // 매출 TOP3
}