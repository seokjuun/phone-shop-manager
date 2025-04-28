package com.mycom.myapp.service.impl;

import com.mycom.myapp.dto.SalesStatDto;
import com.mycom.myapp.dto.SaleTopEmployeeDto;
import com.mycom.myapp.repository.SaleRepository;
import com.mycom.myapp.repository.DeviceModelRepository;
import com.mycom.myapp.repository.CustomerRepository;
import com.mycom.myapp.service.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final SaleRepository saleRepository;
    private final DeviceModelRepository deviceModelRepository;
    private final CustomerRepository customerRepository;

    @Override
    public SalesStatDto getMySalesStat(Long employeeId) {
        return saleRepository.findSalesStatByEmployeeId(employeeId)
                .orElse(new SalesStatDto(0L, 0L, 0L));
    }

    @Override
    public SalesStatDto getTotalSalesStat() {
        return saleRepository.findSalesStatAll()
                .orElse(new SalesStatDto(0L, 0L, 0L));
    }

    @Override
    public Long countLowStockDevices() {
        return deviceModelRepository.countLowStockDevices();
    }

    @Override
    public Long countNewCustomersThisMonth() {
        return customerRepository.countNewCustomersThisMonth();
    }

    @Override
    public List<SaleTopEmployeeDto> findTop3EmployeesThisMonth() {
        return saleRepository.findTop3EmployeesThisMonth(PageRequest.of(0, 3));
    }
}