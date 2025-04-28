package com.mycom.myapp.service.impl;

import com.mycom.myapp.dto.CustomerDto;
import com.mycom.myapp.dto.CustomerRequestDto;
import com.mycom.myapp.entity.Customer;
import com.mycom.myapp.repository.CustomerRepository;
import com.mycom.myapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<CustomerDto> findAll() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();

        customerList.forEach(customer -> {
            CustomerDto customerDto = toDto(customer);
            customerDtoList.add(customerDto);
        });
        return customerDtoList;
    }

    @Override
    public CustomerDto findById(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            return toDto(customer);
        } else {
            throw new RuntimeException("고객을 찾을 수 없습니다.");
        }
    }

    @Override
    public CustomerDto update(Long customerId, CustomerRequestDto dto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();

            customer.setName(dto.getName());
            customer.setPhoneNumber(dto.getPhoneNumber());
            customer.setEmail(dto.getEmail());

            Customer updated = customerRepository.save(customer);
            return toDto(updated);

        } else {
            throw new RuntimeException("고객을 찾을 수 없습니다.");
        }
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
