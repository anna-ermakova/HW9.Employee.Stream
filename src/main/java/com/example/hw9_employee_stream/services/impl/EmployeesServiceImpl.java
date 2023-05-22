package com.example.hw9_employee_stream.services.impl;

import com.example.hw9_employee_stream.exceptions.EmployeeAlreadyAddedException;
import com.example.hw9_employee_stream.exceptions.EmployeeNotFoundException;
import com.example.hw9_employee_stream.exceptions.EmployeeStorageIsFullException;
import com.example.hw9_employee_stream.model.Employee;
import com.example.hw9_employee_stream.services.EmployeesService;
import com.example.hw9_employee_stream.services.ValidatorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeesServiceImpl implements EmployeesService {
    private final List<Employee> employees = new ArrayList<>();
    private final ValidatorService validatorService;

    private static final int LIMIT = 10;

    public EmployeesServiceImpl(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public Employee addEmployee(String name,
                                String surname,
                                int department,
                                double salary) {
        Employee employee = new Employee(
                validatorService.validateName(name),
                validatorService.validateSurname(surname),
                department,
                salary
        );
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    @Override
    public Employee removeEmployee(String name,
                                   String surname) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getName().equals(name) && emp.getSurname().equals(surname))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee getEmployee(String name,
                                String surname) {
        return employees.stream()
                .filter(employee -> employee.getName().equals(name) && employee.getSurname().equals(surname))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public List<Employee> getAllEmp() {
        return new ArrayList<>(employees);
    }

}

