package org.Employees.Data;

import org.junit.jupiter.api.Test;

import static org.junit.gen5.api.Assertions.assertEquals;
import static org.junit.gen5.api.Assertions.assertNull;

public class EmployeeTest {
    @Test
    void testEmployeeBuilder() {
        Employee emp = new Employee.Builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .salary(100000)
                .managerId(null)
                .build();

        assertEquals(1, emp.id);
        assertEquals("John", emp.firstName);
        assertEquals("Doe", emp.lastName);
        assertEquals(100000, emp.salary);
        assertNull(emp.managerId);
    }
}
