package com.mycom.myapp.dto;


import com.mycom.myapp.entity.Sale.DiscountType;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequestDto {
    private Long customerId;
    private Long employeeId;
    private Long modelId;
    private Long planId;
    private Long contractTypeId;
    private Long paymentTermId;
    private DiscountType discountType;
}