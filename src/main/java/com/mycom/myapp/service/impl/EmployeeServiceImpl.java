package com.mycom.myapp.service.impl;

import com.mycom.myapp.dto.EmployeeDto;
import com.mycom.myapp.dto.EmployeeResultDto;
import com.mycom.myapp.entity.Employee;
import com.mycom.myapp.entity.Role;
import com.mycom.myapp.repository.EmployeeRepository;
import com.mycom.myapp.repository.RoleRepository;
import com.mycom.myapp.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public EmployeeResultDto register(EmployeeDto dto) {
        EmployeeResultDto resultDto = new EmployeeResultDto();

        try {
            Role roleStaff = roleRepository.findByRoleName("스태프");
            Set<Role> roles = Set.of(roleStaff);

            Employee employee = Employee.builder()
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .password(dto.getPassword())
                    .registerDate(LocalDateTime.now())
                    .roles(roles)
                    .build();

            Employee saved = employeeRepository.save(employee);

            resultDto.setEmployeeDto(toDto(saved));
            resultDto.setResult("success");
        }   catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            resultDto.setResult("fail");
        }


        return resultDto;
    }

    /*
    Employee.roles는 @ManyToMany(fetch = FetchType.LAZY) 로 설정되어 있음
	findAll()에서 employeeRepository.findAll()로 데이터를 조회한 뒤, 트랜잭션(세션)이 닫힌 상태에서 roles에 접근함
	이 때 Hibernate는 세션 밖에서 LAZY 필드에 접근했기 때문에 예외를 던짐
	 => #1. @Transactional 로 findAll() 메서드에 트랜잭션 유지하기
     */
//    @Override
//    @Transactional
//    public List<EmployeeDto> findAll() {
//        return employeeRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
//    }
    // #2. 위 문제를  roles 를 미리 Fetch Join 으로 강제 로딩 (JPQL 커스텀 쿼리 사용) 하여 해결
    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAllWithRoles().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(Long id) {
        return employeeRepository.findByIdWithRoles(id).map(this::toDto).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeResultDto login(String email, String password) {
        EmployeeResultDto employeeResultDto = new EmployeeResultDto();

        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);

        if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();

            if (employee.getPassword().equals(password)){
                EmployeeDto employeeDto = toDto(employee);

                employeeResultDto.setEmployeeDto(employeeDto);
                employeeResultDto.setResult("success");
            } else{
                employeeResultDto.setResult("fail");
            }
        } else{
            employeeResultDto.setResult("notfound");
        }

        return employeeResultDto;
    }


    private EmployeeDto toDto(Employee e) {
        return EmployeeDto.builder()
                .employeeId(e.getEmployeeId())
                .name(e.getName())
                .email(e.getEmail())
                .registerDate(e.getRegisterDate())
                .roles(e.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet()))
                .build();
    }
}
