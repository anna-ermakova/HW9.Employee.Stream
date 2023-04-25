package com.example.hw9_employee_stream.services;

import com.example.hw9_employee_stream.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee maxSalaryEmp(int department);

    Employee minSalaryEmp(int department);

    Map<Integer, List<Employee>> printAll();

    List<Employee> printEmpDepartment(int id);
}
