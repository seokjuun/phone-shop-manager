package com.mycom.myapp.controller;

import com.mycom.myapp.dto.EmployeeDto;
import com.mycom.myapp.dto.EmployeeRequestDto;
import com.mycom.myapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<EmployeeDto> register(@RequestBody EmployeeRequestDto dto) {
        return ResponseEntity.ok(employeeService.register(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

//    @DeleteMapping("/{id}")
    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
