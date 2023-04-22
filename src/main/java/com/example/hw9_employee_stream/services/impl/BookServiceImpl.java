package com.example.hw9_employee_stream.services.impl;

import com.example.hw9_employee_stream.exceptions.ExistsException;
import com.example.hw9_employee_stream.model.Employee;
import com.example.hw9_employee_stream.services.BookService;
import org.springframework.stereotype.Service;



import java.util.HashMap;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    private Map<String, Employee> staff = new HashMap<>();

    @Override
    public void addEmployee(Employee employee) {
        if (staff.keySet().stream().anyMatch(name -> name.equals(employee.getName()))) {
            throw new ExistsException("такой сотрудник уже есть");
        } else {
            staff.put(employee.getName(), employee);
        }
    }

    @Override
    public void removeEmployee(String name) {
        if (!staff.containsKey(name)) {
            throw new ExistsException("Сотрудник не найден.");
        }
        staff.remove(name);
    }

    @Override
    public Employee getEmployee(String name) {
        if (!staff.containsKey(name)) {
            throw new ExistsException("Сотрудник не найден.");
        }
        return staff.get(name);
    }

    @Override
    public Map<String, Employee> getStaff() {
        return staff;
    }
}

