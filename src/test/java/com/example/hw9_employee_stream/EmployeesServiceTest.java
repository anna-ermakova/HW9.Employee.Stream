package com.example.hw9_employee_stream;

import com.example.hw9_employee_stream.exceptions.*;
import com.example.hw9_employee_stream.model.Employee;
import com.example.hw9_employee_stream.services.ValidatorService;
import com.example.hw9_employee_stream.services.impl.EmployeesServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EmployeesServiceTest {
    private final EmployeesServiceImpl employeesService = new EmployeesServiceImpl(new ValidatorService());

    public static Stream<Arguments> addNegativeNameTestParams() {
        return Stream.of(
                Arguments.of("Иван1"),
                Arguments.of("Иван!"),
                Arguments.of("Иван*")
        );
    }

    public static Stream<Arguments> addNegativeSurnameTestParams() {
        return Stream.of(
                Arguments.of("Иванов1"),
                Arguments.of("Иванов!"),
                Arguments.of("Иванов*")
        );
    }

    @BeforeEach
    public void beforeEach() {
        employeesService.addEmployee("Иван", "Иванов", 1, 10_000);
        employeesService.addEmployee("Петр", "Петров", 1, 20_000);
        employeesService.addEmployee("Анна", "Андреева", 2, 15_000);
        employeesService.addEmployee("Светлана", "Светлова", 2, 5_000);
        employeesService.addEmployee("Сергеев", "Сергей", 3, 1_000);
        employeesService.addEmployee("Семен", "Семенов", 1, 2_000);
    }

    @AfterEach
    public void afterEach() {
        employeesService.getAllEmp()
                .forEach(employee -> employeesService.removeEmployee(employee.getName(), employee.getSurname()));
    }

    @Test
    public void addTest() {
        int beforeCount = employeesService.getAllEmp().size();
        Employee expected = new Employee("Вася", "Пупкин", 1, 1_000);

        Assertions.assertThat(employeesService.addEmployee("Вася", "Пупкин", 1, 1_000))
                .isEqualTo(expected)
                .isIn(employeesService.getAllEmp());
        Assertions.assertThat(employeesService.getAllEmp()).hasSize(beforeCount + 1);
        Assertions.assertThat(employeesService.getEmployee("Вася", "Пупкин")).isEqualTo(expected);
        ;
    }

    @ParameterizedTest
    @MethodSource("addNegativeNameTestParams")
    public void addNegativeNameTest(String negativeName) {
        Assertions.assertThatExceptionOfType(IncorrectNameException.class)
                .isThrownBy(() -> employeesService.addEmployee(negativeName, "Иванов", 1, 1_000));
    }

    @ParameterizedTest
    @MethodSource("addNegativeSurnameTestParams")
    public void addNegativeSurnameTest(String negativeSurname) {
        Assertions.assertThatExceptionOfType(IncorrectSurnameException.class)
                .isThrownBy(() -> employeesService.addEmployee("Иван", negativeSurname, 1, 1_000));
    }

    @Test
    public void addWhenAlreadyExistsTest() {
        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeesService.addEmployee("Иван", "Иванов", 1, 10_000));
    }

    @Test
    public void addWhenStorageIsFullExceptionTest() {
        Stream.iterate(1, i -> i + 1)
                .limit(4)
                .map(number -> new Employee(
                                "Иван" + ((char) ('a' + number)),
                                "Иванов" + ((char) ('a' + number)),
                                number,
                                10_000 + number
                        )
                )
                .forEach(employee -> employeesService.addEmployee(
                        employee.getName(),
                        employee.getSurname(),
                        employee.getDepartment(),
                        employee.getSalary()));

        Assertions.assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeesService.addEmployee("Гена", "Гиенов", 1, 123));
    }

    @Test
    public void removeTest() {
        int beforeCount = employeesService.getAllEmp().size();
        Employee expected = new Employee("Иван", "Иванов", 1, 10_000);

        Assertions.assertThat(employeesService.removeEmployee("Иван", "Иванов"))
                .isEqualTo(expected)
                .isNotIn(employeesService.getAllEmp());
        Assertions.assertThat(employeesService.getAllEmp()).hasSize(beforeCount - 1);
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeesService.getEmployee("Иван", "Иванов"))
        ;
    }

    @Test
    public void removeNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeesService.getEmployee("Гена", "Гиенов"))
        ;
    }

    @Test
    public void getTest() {
        int beforeCount = employeesService.getAllEmp().size();
        Employee expected = new Employee("Иван", "Иванов", 1, 10_000);

        Assertions.assertThat(employeesService.getEmployee("Иван", "Иванов"))
                .isEqualTo(expected)
                .isIn(employeesService.getAllEmp());
        Assertions.assertThat(employeesService.getAllEmp()).hasSize(beforeCount);
    }

    @Test
    public void getNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeesService.getEmployee("Гена", "Гиенов"))
        ;
    }

    @Test
    public void getAllTest() {
        Assertions.assertThat(employeesService.getAllEmp())
                .hasSize(6)
                .containsExactlyInAnyOrder(
                        new Employee("Иван", "Иванов", 1, 10_000),
                        new Employee("Петр", "Петров", 1, 20_000),
                        new Employee("Анна", "Андреева", 2, 15_000),
                        new Employee("Светлана", "Светлова", 2, 5_000),
                        new Employee("Сергеев", "Сергей", 3, 1_000),
                        new Employee("Семен", "Семенов", 1, 2_000)
                );
    }
}

