package com.mycom.myapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long customerId;
    private String name;
    private String phoneNumber;
    private String email;
    private LocalDateTime registerDate;
}