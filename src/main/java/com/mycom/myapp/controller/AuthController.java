package com.mycom.myapp.controller;

import com.mycom.myapp.dto.EmployeeResultDto;
import com.mycom.myapp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "인증 API", description = "로그인 및 로그아웃 처리")
public class AuthController {

    private final EmployeeService employeeService;

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인")
    public EmployeeResultDto login(
            @Parameter(description = "이메일", example = "user@example.com")
            @RequestParam("email") String email,

            @Parameter(description = "비밀번호", example = "1234")
            @RequestParam("password") String password,

            HttpSession session) {

        EmployeeResultDto employeeResultDto = employeeService.login(email, password);
        if (employeeResultDto.getResult().equals("success")) {
            session.setAttribute("employeeDto", employeeResultDto.getEmployeeDto());
        }
        return employeeResultDto;
    }

    @GetMapping("/logout")
    @Operation(summary = "로그아웃", description = "현재 세션을 무효화하고 로그아웃")
    public EmployeeResultDto logout(HttpSession session) {
        EmployeeResultDto employeeResultDto = new EmployeeResultDto();

        session.invalidate();
        employeeResultDto.setResult("success");

        return employeeResultDto;
    }
}