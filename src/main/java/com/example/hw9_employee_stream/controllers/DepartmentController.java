package com.example.hw9_employee_stream.controllers;

import com.example.hw9_employee_stream.model.Employee;
import com.example.hw9_employee_stream.services.DepartmentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentsService departmentsService;

    public DepartmentController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @GetMapping("/max-salary")
    public double employeeWithMaxSalaryFromDepartment(@RequestParam int departmentId) {
        return departmentsService.empWithMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public double employeeWithMinSalaryFromDepartment(@RequestParam int departmentId) {
        return departmentsService.empWithMinSalary(departmentId);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> employeesFromDepartment(@RequestParam int departmentId) {
        return departmentsService.employeesFromDepartment(departmentId);
    }
}
