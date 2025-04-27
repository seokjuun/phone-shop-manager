package com.mycom.myapp.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private Long employeeId;
    private String name;
    private String email;
    private String password;
    private Set<String> roles;
    private LocalDateTime registerDate;
}