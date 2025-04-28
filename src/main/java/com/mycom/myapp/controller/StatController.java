package com.mycom.myapp.controller;

import com.mycom.myapp.dto.*;
import com.mycom.myapp.service.StatService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/stat")
@RequiredArgsConstructor
public class StatController {

    private final StatService statService;

    @GetMapping("/check")
    public StatSummaryDto getStatSummary(HttpSession session) {
        EmployeeDto employeeDto = (EmployeeDto) session.getAttribute("employeeDto");

        if (employeeDto == null) {
            throw new RuntimeException("로그인이 필요");
        }

        Long employeeId = employeeDto.getEmployeeId();
        Set<String> roles = employeeDto.getRoles();

        SalesStatDto myStat = statService.getMySalesStat(employeeId);

        SalesStatDto totalStat;
        if (roles.contains("점장") || roles.contains("매니저")) {
            totalStat = statService.getTotalSalesStat();
        } else {
            totalStat = new SalesStatDto(0L, 0L, 0L); // 스태프는 전체 매출 조회 불가
        }

        Long lowStockCount = statService.countLowStockDevices();
        Long newCustomerCount = statService.countNewCustomersThisMonth();

        List<SaleTopEmployeeDto> top3Employees = statService.findTop3EmployeesThisMonth();

        return new StatSummaryDto(myStat, totalStat, lowStockCount, newCustomerCount, top3Employees);
    }
}