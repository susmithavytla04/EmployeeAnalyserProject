package org.Employees.Managers;

import org.Employees.Analysis.CSVParser;
import org.Employees.Analysis.DataLoader;
import org.Employees.Analysis.Parser;
import org.Employees.Data.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.Employees.Analysis.DataLoader.parseEmployees;

public class ManagerDataAnalyser {
    public static Map<Integer, Employee> employees = new HashMap<>();
    public static Map<Integer, List<Employee>> managerToSubordinates = new HashMap<>();
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the CSV file: ");
        String filePath = scanner.nextLine();
        scanner.close();

        Parser p= new CSVParser();
        List<String> lines = p.parse(filePath);
        employees= parseEmployees(lines, managerToSubordinates);
        analyzeSalaries();
        analyzeReportingDepth();
    }
    public static void analyzeSalaries() {
        for (Map.Entry<Integer, List<Employee>> entry : managerToSubordinates.entrySet()) {
            Employee manager = employees.get(entry.getKey());
            List<Employee> subordinates = entry.getValue();

            double avgSubSalary = subordinates.stream().mapToInt(e -> e.salary).average().orElse(0);
            double minRequiredSalary = avgSubSalary * 1.2;
            double maxAllowedSalary = avgSubSalary * 1.5;

            if (manager.salary < minRequiredSalary) {
                System.out.println(manager.firstName + " " + manager.lastName + " earns too little by " + (minRequiredSalary - manager.salary));
            } else if (manager.salary > maxAllowedSalary) {
                System.out.println(manager.firstName + " " + manager.lastName + " earns too much by " + (manager.salary - maxAllowedSalary));
            }
        }
    }

    public static void analyzeReportingDepth() {
        for (Employee employee : employees.values()) {
            int depth = getReportingDepth(employee);
            if (depth > 4) {
                System.out.println(employee.firstName + " " + employee.lastName + " has a long line  of " + depth);
            }
        }
    }
    public static int getReportingDepth(Employee emp) {
        int depth = 0;
        while (emp.managerId != null) {
            emp = employees.get(emp.managerId);
            depth++;
        }
        return depth;
    }
}
