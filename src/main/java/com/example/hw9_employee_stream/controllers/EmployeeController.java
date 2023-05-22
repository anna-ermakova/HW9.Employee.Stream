package com.example.hw9_employee_stream.controllers;

import com.example.hw9_employee_stream.model.Employee;
import com.example.hw9_employee_stream.services.DepartmentsService;
import com.example.hw9_employee_stream.services.EmployeesService;
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

import java.util.List;


@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor

public class EmployeeController {
    private final EmployeesService employeesService;

    @GetMapping
    public String welcome() {
        return "Welcome to departments";
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String name,
                        @RequestParam("lastName") String surname,
                        @RequestParam int department,
                        @RequestParam double salary) {
        return employeesService.addEmployee(name, surname, department, salary);
    }

    @GetMapping("/remove")
    @Operation(summary = "Удаление сотрудника")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Сотрудник удален")
    })
    public Employee remove(@RequestParam("firstName") String name,
                           @RequestParam("lastName") String surname) {
        return employeesService.removeEmployee(name, surname);
    }

    @GetMapping("/get")
    public Employee get(@RequestParam("firstName") String name,
                         @RequestParam("lastName") String surname) {
        return employeesService.getEmployee(name, surname);
    }
    @GetMapping("/getAll")
    public List<Employee> getAll() {
        return employeesService.getAllEmp();
    }
}