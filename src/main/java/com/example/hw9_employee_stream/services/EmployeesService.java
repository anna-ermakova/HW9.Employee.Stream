package com.example.hw9_employee_stream.services;

import com.example.hw9_employee_stream.model.Employee;

import java.util.List;

public interface EmployeesService {


    Employee addEmployee(String name,
                         String surname,
                         int department,
                         double salary);

    Employee removeEmployee(String name,
                            String surname);

    Employee getEmployee(String name,
                         String surname);

    List<Employee> getAllEmp();
}
