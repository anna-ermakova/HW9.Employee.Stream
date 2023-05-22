package com.example.hw9_employee_stream;

import com.example.hw9_employee_stream.exceptions.DepartmentNotFoundException;
import com.example.hw9_employee_stream.model.Employee;
import com.example.hw9_employee_stream.services.EmployeesService;
import com.example.hw9_employee_stream.services.impl.DepartmentsServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeesService employeesService;
    @InjectMocks
    private DepartmentsServiceImpl departmentsServiceImpl;

    public static Stream<Arguments> empWithMaxSalaryTestParams() {
        return Stream.of(
                Arguments.of(1, 20_000),
                Arguments.of(2, 15_000),
                Arguments.of(3, 2_000)
        );
    }

    public static Stream<Arguments> empWithMinSalaryTestParams() {
        return Stream.of(
                Arguments.of(1, 10_000),
                Arguments.of(2, 5_000),
                Arguments.of(3, 1_000)
        );
    }

    public static Stream<Arguments> employeesFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1,
                        List.of(
                                new Employee("Иван", "Иванов", 1, 10_000),
                                new Employee("Петр", "Петров", 1, 20_000)
                        ),
                        Arguments.of(2,
                                new Employee("Анна", "Андреева", 2, 15_000),
                                new Employee("Светлана", "Светлова", 2, 5_000)
                        ),
                        Arguments.of(3,
                                new Employee("Сергеев", "Сергей", 3, 1_000),
                                new Employee("Семен", "Семенов", 3, 2_000)
                        ),
                        Arguments.of(4,
                                Collections.emptyList())));
    }

    public static Stream<Arguments> empWithSumSalaryTestParams() {
        return Stream.of(
                Arguments.of(1, 30_000),
                Arguments.of(2, 20_000),
                Arguments.of(3, 3_000),
                Arguments.of(4, 0)
        );
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.when(employeesService.getAllEmp()).thenReturn(
                List.of(
                        new Employee("Иван", "Иванов", 1, 10_000),
                        new Employee("Петр", "Петров", 1, 20_000),
                        new Employee("Анна", "Андреева", 2, 15_000),
                        new Employee("Светлана", "Светлова", 2, 5_000),
                        new Employee("Сергеев", "Сергей", 3, 1_000),
                        new Employee("Семен", "Семенов", 3, 2_000)
                ));
    }

    @ParameterizedTest
    @MethodSource("empWithMaxSalaryTestParams")
    public void empWithMaxSalaryTest(int departmentId, double expected) {
        Assertions.assertThat(departmentsServiceImpl.empWithMaxSalary(departmentId))
                .isEqualTo(expected);
    }

    @Test
    public void empWithMaxSalaryWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentsServiceImpl.empWithMaxSalary(4));
    }

    @ParameterizedTest
    @MethodSource("empWithMinSalaryTestParams")
    public void empWithMinSalaryTest(int departmentId, double expected) {
        Assertions.assertThat(departmentsServiceImpl.empWithMinSalary(departmentId))
                .isEqualTo(expected);
    }

    @Test
    public void empWithMinSalaryWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentsServiceImpl.empWithMinSalary(4));
    }

    @ParameterizedTest
    @MethodSource("empWithSumSalaryTestParams")
    public void empWithSumSalaryTest(int departmentId, double expected) {
        Assertions.assertThat(departmentsServiceImpl.sumSalaryFromDepartment(departmentId))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("employeesFromDepartmentTestParams")
    public void employeesFromDepartmentTest(int departmentId, List<Employee> expected) {
        Assertions.assertThat(departmentsServiceImpl.employeesFromDepartment(departmentId))
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void employeesGroupedByDepartmentTest() {
        Map<Integer, List<Employee>> expected = Map.of(
                1,
                List.of(
                        new Employee("Иван", "Иванов", 1, 10_000),
                        new Employee("Петр", "Петров", 1, 20_000)),
                2,
                List.of(
                        new Employee("Анна", "Андреева", 2, 15_000),
                        new Employee("Светлана", "Светлова", 2, 5_000)),
                3,
                List.of(
                        new Employee("Сергеев", "Сергей", 3, 1_000),
                        new Employee("Семен", "Семенов", 3, 2_000)));


        Assertions.assertThat(departmentsServiceImpl.employeesGroupedByDepartment())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }
}
