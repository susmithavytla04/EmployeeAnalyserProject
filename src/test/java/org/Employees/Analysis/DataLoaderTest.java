package org.Employees.Analysis;

import org.Employees.Data.Employee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataLoaderTest {
    @Test
    void testParseEmployees() {
        List<String> lines = Arrays.asList(
                "id,firstname,lastname,salary,managerid",
                "1,John,Doe,100000,",
                "2,Jane,Smith,80000,1",
                "3,Jim,Brown,75000,1"
        );
        Map<Integer, List<Employee>> managerToSubordinates = new HashMap<>();
        Map<Integer, Employee> employees = DataLoader.parseEmployees(lines, managerToSubordinates);

        assertEquals(3, employees.size());
        assertEquals("John", employees.get(1).firstName);
        assertTrue(managerToSubordinates.containsKey(1));
        assertEquals(2, managerToSubordinates.get(1).size());
    }
}
