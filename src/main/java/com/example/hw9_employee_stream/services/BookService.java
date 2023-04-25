package com.example.hw9_employee_stream.services;

import com.example.hw9_employee_stream.model.Employee;

import java.util.Map;

public interface BookService {
    void addEmployee(Employee employee);

    void removeEmployee(String name);

    Employee getEmployee(String name);

    Map<String, Employee> getStaff();
}
