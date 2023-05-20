package com.example.hw9_employee_stream.services.impl;

import com.example.hw9_employee_stream.model.Employee;
import com.example.hw9_employee_stream.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Employee[] employees = new Employee[100];


    EmployeeServiceImpl() {
        employees[0] = new Employee("Иванов", 50_000, 1);
        employees[1] = new Employee("Петров", 1_000_000, 1);
        employees[2] = new Employee("Сергеев", 2_000_000, 2);
        employees[3] = new Employee("Андреева", 250_000, 2);
        employees[4] = new Employee("Викторов", 15_000, 2);
        employees[5] = new Employee("Егоров", 75_000, 3);
        employees[6] = new Employee("Максимов", 127_000, 4);
        employees[7] = new Employee("Ульянова", 58_000, 5);
    }


    @Override
    public Employee maxSalaryEmp(int department) {
        return stream(employees)
                .filter(employees -> employees.getDepartment() == department)
                .max(Comparator.comparingDouble(employees -> employees.getSalary()))
                .orElseThrow(() -> new IllegalArgumentException("Нет такого департамента!"));
    }

    @Override
    public Employee minSalaryEmp(int department) {
        return stream(employees)
                .filter(employees -> employees.getDepartment() == department)
                .min(Comparator.comparingDouble(employees -> employees.getSalary()))
                .orElseThrow(() -> new IllegalArgumentException("Нет такого департамента!"));
    }

    @Override
    public Map<Integer, List<Employee>> printAll() {
        return stream(employees)
                .sorted(Comparator.comparingInt(employees -> employees.getDepartment()))
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public List<Employee> printEmpDepartment(int id) {
        return stream(employees)
                .filter(employees -> employees.getDepartment() == id)
                .collect(Collectors.toList());
    }

}