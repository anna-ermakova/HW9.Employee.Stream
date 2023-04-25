package com.example.hw9_employee_stream.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Employee {
    @NotBlank(message = "Обязательно для заполнения!")
    @JsonProperty("name")
    private String name;
    @Positive
    private double salary;
    private int department;
}
