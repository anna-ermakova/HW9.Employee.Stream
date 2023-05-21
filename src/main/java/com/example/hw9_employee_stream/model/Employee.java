package com.example.hw9_employee_stream.model;


import com.example.hw9_employee_stream.services.ValidatorService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


@EqualsAndHashCode
@ToString
public class Employee {
    public ValidatorService validateService;
    @NotBlank(message = "Обязательно для заполнения!")
    @JsonProperty("name")
    private String name;
    private String surname;

    @Positive
    private double salary;
    private int department;

    public Employee(String name, String surname, int department, double salary) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;

    }

    public String getSurname() {
        return surname;
    }

    public void setSurName(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }
}
