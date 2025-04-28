package com.mycom.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleTopEmployeeDto {
    private String employeeName;
    private Long totalAmount;
}