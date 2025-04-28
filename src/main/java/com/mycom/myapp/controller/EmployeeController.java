package com.mycom.myapp.controller;

import com.mycom.myapp.dto.EmployeeDto;
import com.mycom.myapp.dto.EmployeeResultDto;

import com.mycom.myapp.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<EmployeeResultDto> register(EmployeeDto dto) {
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


    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public EmployeeResultDto login(@RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   HttpSession session){
        EmployeeResultDto employeeResultDto = employeeService.login(email, password);
        if (employeeResultDto.getResult().equals("success")){
            session.setAttribute("employeeDto", employeeResultDto.getEmployeeDto());
        }
        return employeeResultDto;
    }

    @GetMapping("/logout")
    public EmployeeResultDto logout(HttpSession session){
        EmployeeResultDto employeeResultDto = new EmployeeResultDto();

        session.invalidate();
        employeeResultDto.setResult("success");

        return employeeResultDto;
    }
}
