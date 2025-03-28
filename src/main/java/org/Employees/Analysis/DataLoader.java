package org.Employees.Analysis;

import org.Employees.Data.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {

    public static Map<Integer, Employee> parseEmployees(List<String> lines, Map<Integer, List<Employee>> managerToSubordinates) {
        Map<Integer, Employee> employees = new HashMap<>();
        if (lines.isEmpty()) return employees;

        String[] headers = lines.get(0).split(",");
        Map<String, Integer> headerIndex = new HashMap<>();

        for (int i = 0; i < headers.length; i++) {
            headerIndex.put(headers[i].trim().toLowerCase(), i);
        }

        for (int i = 1; i < lines.size(); i++) { // Skip header row
            String[] parts = lines.get(i).split(",");
            int id = Integer.parseInt(parts[headerIndex.get("id")]);
            String firstName = parts[headerIndex.get("firstname")];
            String lastName = parts[headerIndex.get("lastname")];
            int salary = Integer.parseInt(parts[headerIndex.get("salary")]);
            Integer managerId;
            if(parts.length<5){
                managerId=null;
            }
            else {
                 managerId = headerIndex.containsKey("managerid") && !parts[headerIndex.get("managerid")].isEmpty()
                        ? Integer.parseInt(parts[headerIndex.get("managerid")])
                        : null;
            }
            Employee emp = new Employee.Builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .salary(salary)
                    .managerId(managerId)
                    .build();

            employees.put(id, emp);

            if (managerId != null) {
                managerToSubordinates.computeIfAbsent(managerId, k -> new ArrayList<>()).add(emp);
            }
        }
        return employees;
    }

}
