package com.mycom.myapp.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResultDto {
//    private String name;
//    private String email;
//    private String password;
//    private Set<Long> roleIds;

    private String result;
    private EmployeeDto employeeDto;
    private List<EmployeeDto> employeeDtoList;

}