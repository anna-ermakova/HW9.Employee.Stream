package com.example.hw9_employee_stream.services.impl;

import com.example.hw9_employee_stream.exceptions.DepartmentNotFoundException;
import com.example.hw9_employee_stream.model.Employee;
import com.example.hw9_employee_stream.services.DepartmentsService;
import com.example.hw9_employee_stream.services.EmployeesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentsServiceImpl implements DepartmentsService {
    private final EmployeesService employeesService;

    public DepartmentsServiceImpl(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @Override
    public double empWithMaxSalary(int departmentId) {
        return employeesService.getAllEmp().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .max()
                .orElseThrow(DepartmentNotFoundException::new);
    }

    @Override
    public double empWithMinSalary(int departmentId) {
        return employeesService.getAllEmp().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow(DepartmentNotFoundException::new);
    }

    @Override
    public double sumSalaryFromDepartment(int departmentId) {
        return employeesService.getAllEmp().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public List<Employee> employeesFromDepartment(int departmentId) {
        return employeesService.getAllEmp().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> employeesGroupedByDepartment() {
        return employeesService.getAllEmp().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}