package com.mycom.myapp.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDto {
    private String name;
    private String email;
    private String password;
    private Set<Long> roleIds;
}