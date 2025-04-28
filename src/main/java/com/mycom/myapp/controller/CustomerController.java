package com.mycom.myapp.controller;

import com.mycom.myapp.dto.CustomerDto;
import com.mycom.myapp.dto.CustomerRequestDto;
import com.mycom.myapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerDto register(@RequestBody CustomerRequestDto dto) {
        return customerService.register(dto);
    }

    @GetMapping
    public Page<CustomerDto> findAll(@RequestParam(defaultValue = "0") int page) {
        return customerService.findAll(page);
    }

    @GetMapping("/{customerId}")
    public CustomerDto findById(@PathVariable Long customerId) {
        return customerService.findById(customerId);
    }

    @PutMapping("/{customerId}")
    public CustomerDto update(@PathVariable Long customerId, @RequestBody CustomerRequestDto dto) {
        return customerService.update(customerId, dto);
    }

    @DeleteMapping("/{customerId}")
    public void delete(@PathVariable Long customerId) {
        customerService.deleteById(customerId);
    }
}