package com.mycom.myapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private DeviceModel deviceModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_type_id")
    private ContractType contractType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id")
    private PaymentTerm paymentTerm;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private Integer devicePriceBeforeDiscount;
    private Integer deviceDiscountTotal;
    private Integer deviceFinalPrice;
    private Integer monthlyDevicePayment;
    private Integer planPriceBeforeDiscount;
    private Integer planDiscountAmount;
    private Integer monthlyPlanPayment;
    private Integer monthlyTotalPayment;

    private LocalDateTime saleDate;

    public enum DiscountType { 공시지원금, 선택약정 }
}
