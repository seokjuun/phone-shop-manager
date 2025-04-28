package com.mycom.myapp.service.impl;

import com.mycom.myapp.dto.CustomerDto;
import com.mycom.myapp.dto.CustomerRequestDto;
import com.mycom.myapp.entity.Customer;
import com.mycom.myapp.repository.CustomerRepository;
import com.mycom.myapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public CustomerDto register(CustomerRequestDto dto) {
        Customer customer = Customer.builder()
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .registerDate(LocalDateTime.now())
                .build();
        Customer saved = customerRepository.save(customer);
        return toDto(saved);
    }

    @Override
    public Page<CustomerDto> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("customerId").descending());
        Page<Customer> customerPage = customerRepository.findAll(pageable);

        return customerPage.map(this::toDto);
    }

    @Override
    public CustomerDto findById(Long customerId) {
        return customerRepository.findById(customerId)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("고객을 찾을 수 없습니다."));
    }

    @Override
    public CustomerDto update(Long customerId, CustomerRequestDto dto) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    customer.setName(dto.getName());
                    customer.setPhoneNumber(dto.getPhoneNumber());
                    customer.setEmail(dto.getEmail());
                    Customer updated = customerRepository.save(customer);
                    return toDto(updated);
                })
                .orElseThrow(() -> new RuntimeException("고객을 찾을 수 없습니다."));
    }

    @Override
    public void deleteById(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    private CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .email(customer.getEmail())
                .registerDate(customer.getRegisterDate())
                .build();
    }
}
