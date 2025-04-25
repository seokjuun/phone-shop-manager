package com.mycom.myapp.service.impl;

import com.mycom.myapp.dto.SaleRequestDto;
import com.mycom.myapp.dto.SaleResponseDto;
import com.mycom.myapp.entity.*;
import com.mycom.myapp.repository.*;
import com.mycom.myapp.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final DeviceModelRepository deviceModelRepository;
    private final PlanRepository planRepository;
    private final ContractTypeRepository contractTypeRepository;
    private final PaymentTermRepository paymentTermRepository;
    private final SaleRepository saleRepository;

    @Override
    public SaleResponseDto calculateAndSaveSale(SaleRequestDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow();
        Employee employee = employeeRepository.findById(dto.getEmployeeId()).orElseThrow();
        DeviceModel device = deviceModelRepository.findById(dto.getModelId()).orElseThrow();
        Plan plan = planRepository.findById(dto.getPlanId()).orElseThrow();
        ContractType contractType = contractTypeRepository.findById(dto.getContractTypeId()).orElseThrow();
        PaymentTerm term = paymentTermRepository.findById(dto.getPaymentTermId()).orElseThrow();

        int deviceDiscount = 0;
        if (dto.getDiscountType() == Sale.DiscountType.공시지원금) {
            deviceDiscount += device.getDeviceSubsidy();
            if (contractType.getType() == ContractType.ContractCategory.번호이동) {
                deviceDiscount += device.getPortingSupport();
            }
        }

        int deviceFinal = device.getLaunchPrice() - deviceDiscount;
        int monthlyDevicePayment = (term.getMonths() == 0) ? 0 :
                (int) Math.round(deviceFinal * (1 + term.getInterestRate() * term.getMonths() / 12) / term.getMonths());

        int planDiscount = dto.getDiscountType() == Sale.DiscountType.선택약정 ? (int)(plan.getBaseFee() * 0.25) : 0;
        int planFinal = plan.getBaseFee() - planDiscount;

        int monthlyTotal = monthlyDevicePayment + planFinal;

        Sale sale = Sale.builder()
                .customer(customer)
                .employee(employee)
                .deviceModel(device)
                .plan(plan)
                .contractType(contractType)
                .paymentTerm(term)
                .discountType(dto.getDiscountType())
                .devicePriceBeforeDiscount(device.getLaunchPrice())
                .deviceDiscountTotal(deviceDiscount)
                .deviceFinalPrice(deviceFinal)
                .monthlyDevicePayment(monthlyDevicePayment)
                .planPriceBeforeDiscount(plan.getBaseFee())
                .planDiscountAmount(planDiscount)
                .monthlyPlanPayment(planFinal)
                .monthlyTotalPayment(monthlyTotal)
                .saleDate(LocalDateTime.now())
                .build();

        saleRepository.save(sale);

        return SaleResponseDto.builder()
                .devicePriceBeforeDiscount(device.getLaunchPrice())
                .deviceDiscountTotal(deviceDiscount)
                .deviceFinalPrice(deviceFinal)
                .monthlyDevicePayment(monthlyDevicePayment)
                .planPriceBeforeDiscount(plan.getBaseFee())
                .planDiscountAmount(planDiscount)
                .monthlyPlanPayment(planFinal)
                .monthlyTotalPayment(monthlyTotal)
                .saleDate(sale.getSaleDate())
                .build();
    }
}
