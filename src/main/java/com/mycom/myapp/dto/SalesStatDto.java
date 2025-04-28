package com.mycom.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesStatDto {
    private Long todaySalesCount;
    private Long monthSalesCount;
    private Long monthSalesTotal;
}