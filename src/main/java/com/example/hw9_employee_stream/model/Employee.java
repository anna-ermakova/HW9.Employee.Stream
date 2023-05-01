package com.example.hw9_employee_stream.model;

import com.example.hw9_employee_stream.exceptions.ExistsException;
import com.example.hw9_employee_stream.services.ValidateService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


@Setter
@Getter
@EqualsAndHashCode
public class Employee {
    public ValidateService validateService;
    @NotBlank(message = "Обязательно для заполнения!")
    @JsonProperty("name")
    private String name;

    @Positive
    private double salary;
    private int department;

    public Employee(String name, double salary, int department) {
        this.name =validateService.validateString(name);
        this.salary = salary;
        this.department = department;
    }
}
