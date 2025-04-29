package com.mycom.myapp.controller;

import com.mycom.myapp.dto.CustomerDto;
import com.mycom.myapp.dto.CustomerRequestDto;
import com.mycom.myapp.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "고객 관리 API", description = "고객 등록, 조회, 수정, 삭제 기능 제공")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "고객 등록", description = "신규 고객을 등록")
    public CustomerDto register(@RequestBody CustomerRequestDto dto) {
        return customerService.register(dto);
    }

    @GetMapping
    @Operation(summary = "고객 목록 조회 (페이징)", description = "페이지 번호에 따른 고객 목록을 조회")
    public Page<CustomerDto> findAll(@RequestParam(defaultValue = "0") int page) {
        return customerService.findAll(page);
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "고객 상세 조회", description = "고객 ID로 고객 상세 정보를 조회")
    public CustomerDto findById(@PathVariable Long customerId) {
        return customerService.findById(customerId);
    }

    @PutMapping("/{customerId}")
    @Operation(summary = "고객 정보 수정", description = "고객 ID에 해당하는 고객 정보를 수정")
    public CustomerDto update(@PathVariable Long customerId, @RequestBody CustomerRequestDto dto) {
        return customerService.update(customerId, dto);
    }

    @DeleteMapping("/{customerId}")
    @Operation(summary = "고객 삭제", description = "고객 ID에 해당하는 고객을 삭제합니다.")
    public void delete(@PathVariable Long customerId) {
        customerService.deleteById(customerId);
    }
}