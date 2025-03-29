package org.Employees.Managers;

import org.Employees.Data.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagerDataAnalysisTest {

    private Map<Integer, Employee> employees;
    private Map<Integer, List<Employee>> managerToSubordinates;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        employees = new HashMap<>();
        managerToSubordinates = new HashMap<>();


        Employee manager = new Employee.Builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .salary(120000)
                .managerId(null)
                .build();
        Employee emp1 =
        new Employee.Builder()
                .id(2)
                .firstName("Jane")
                .lastName("Smith")
                .salary(80000)
                .managerId(1)
                .build();
        Employee emp2 =
        new Employee.Builder()
                .id(3)
                .firstName("Jim")
                .lastName("Brown")
                .salary(75000)
                .managerId(1)
                .build();
        Employee emp3 =
        new Employee.Builder()
                .id(4)
                .firstName("Jake")
                .lastName("White")
                .salary(70000)
                .managerId(2)
                .build();
        employees.put(1, manager);
        employees.put(2, emp1);
        employees.put(3, emp2);
        employees.put(4, emp3);

        managerToSubordinates.put(1, Arrays.asList(emp1, emp2));
        managerToSubordinates.put(2, List.of(emp3));

        ManagerDataAnalyser.employees = employees;
        ManagerDataAnalyser.managerToSubordinates = managerToSubordinates;

        // Redirect console output
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testAnalyzeSalaries() {
        ManagerDataAnalyser.analyzeSalaries();
        String output = outputStream.toString().trim();

        // Validate console output
        assertTrue(output.contains("John Doe earns too little") || output.contains("John Doe earns too much"));
    }

    @Test
    void testAnalyzeReportingDepth() {
        assertEquals(0, ManagerDataAnalyser.getReportingDepth(employees.get(1))); // Manager
        assertEquals(1, ManagerDataAnalyser.getReportingDepth(employees.get(2))); // Employee under Manager
        assertEquals(2, ManagerDataAnalyser.getReportingDepth(employees.get(4))); // Employee under Employee
    }
}
