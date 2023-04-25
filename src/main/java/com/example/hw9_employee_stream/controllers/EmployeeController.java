package com.example.hw9_employee_stream.controllers;

import com.example.hw9_employee_stream.model.Employee;
import com.example.hw9_employee_stream.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor

public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public String welcome() {
        return "Welcome to departmens";
    }

    @GetMapping("/max-salary")
    public Employee maxSalaryEmp(@RequestParam("departmentId") int department) {
        return employeeService.maxSalaryEmp(department);
    }

    @GetMapping("/min-salary")
    @Operation(summary = "Получение сотрудника с минимальной зарплатой по отделу.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Минимальная зарплата получена",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Employee.class)
                            )
                    }
            )
    })
    public Employee minSalaryEmp(@RequestParam("departmentId") int department) {
        return employeeService.minSalaryEmp(department);
    }

    @GetMapping("/all")
    public Object allEmp(@RequestParam(value = "departmentId", required = false) Integer department) {
        if (department == null)
            return employeeService.printAll();
        else
            return employeeService.printEmpDepartment(department);
    }
}