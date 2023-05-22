package com.example.hw9_employee_stream.services;

import com.example.hw9_employee_stream.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentsService {


    double empWithMaxSalary(int department);


    double empWithMinSalary(int department);

    double sumSalaryFromDepartment(int departmentId);

    List<Employee> employeesFromDepartment(int departmentId);

    Map<Integer, List<Employee>> employeesGroupedByDepartment();
}
