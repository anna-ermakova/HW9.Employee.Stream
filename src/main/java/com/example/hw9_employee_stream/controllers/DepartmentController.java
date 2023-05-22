package com.example.hw9_employee_stream.controllers;

import com.example.hw9_employee_stream.model.Employee;
import com.example.hw9_employee_stream.services.DepartmentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentsService departmentsService;

    public DepartmentController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @GetMapping("/{id}/salary/max")
    public double employeeWithMaxSalaryFromDepartment(@RequestParam int departmentId) {
        return departmentsService.empWithMaxSalary(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public double employeeWithMinSalaryFromDepartment(@RequestParam int departmentId) {
        return departmentsService.empWithMinSalary(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public double sumSalaryFromDepartment(@PathVariable int departmentId) {
        return departmentsService.sumSalaryFromDepartment(departmentId);
    }
    @GetMapping(value = "/{id}/employees")
    public List<Employee> employeesFromDepartment(@PathVariable int departmentId) {
        return departmentsService.employeesFromDepartment(departmentId);
    }
}
