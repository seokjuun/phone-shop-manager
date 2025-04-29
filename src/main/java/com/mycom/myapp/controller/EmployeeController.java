package com.mycom.myapp.controller;

import com.mycom.myapp.dto.EmployeeDto;
import com.mycom.myapp.dto.EmployeeResultDto;

import com.mycom.myapp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "직원 관리 API", description = "직원 등록, 조회, 삭제 관련 기능을 제공")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    @Operation(summary = "직원 등록", description = "신규 직원을 등록")
    public ResponseEntity<EmployeeResultDto> register(@RequestBody EmployeeDto dto) {
        return ResponseEntity.ok(employeeService.register(dto));
    }

    @GetMapping("/employees")
    @Operation(summary = "직원 목록 조회", description = "모든 직원을 조회")
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/employees/{id}")
    @Operation(summary = "직원 상세 조회", description = "직원 ID를 통해 직원 정보를 조회")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }


    @DeleteMapping("/employees/{id}")
    @Operation(summary = "직원 삭제", description = "직원 ID를 통해 해당 직원을 삭제")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
