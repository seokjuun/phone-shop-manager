package com.mycom.myapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDto {
    private String name;
    private String phoneNumber;
    private String email;
}