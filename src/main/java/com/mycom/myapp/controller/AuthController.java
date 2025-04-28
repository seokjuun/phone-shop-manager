package com.mycom.myapp.controller;


import com.mycom.myapp.dto.EmployeeResultDto;
import com.mycom.myapp.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final EmployeeService employeeService;

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
