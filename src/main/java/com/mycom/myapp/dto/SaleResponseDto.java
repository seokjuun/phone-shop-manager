package com.mycom.myapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponseDto {
    private Integer devicePriceBeforeDiscount;
    private Integer deviceDiscountTotal;
    private Integer deviceFinalPrice;
    private Integer monthlyDevicePayment;
    private Integer planPriceBeforeDiscount;
    private Integer planDiscountAmount;
    private Integer monthlyPlanPayment;
    private Integer monthlyTotalPayment;
    private LocalDateTime saleDate;
}