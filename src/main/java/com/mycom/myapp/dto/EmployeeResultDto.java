package com.mycom.myapp.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResultDto {

    private String result;
    private EmployeeDto employeeDto;
    private List<EmployeeDto> employeeDtoList;

}