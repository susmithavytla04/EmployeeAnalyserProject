package org.Employees.Data;

public class Employee {
    public int id;
    public String firstName;
    public String lastName;
    public int salary;
   public  Integer managerId;

    private Employee(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.salary = builder.salary;
        this.managerId = builder.managerId;
    }

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private int salary;
        private Integer managerId;

        public Builder id(int id) { this.id = id; return this; }
        public Builder firstName(String firstName) { this.firstName = firstName; return this; }
        public Builder lastName(String lastName) { this.lastName = lastName; return this; }
        public Builder salary(int salary) { this.salary = salary; return this; }
        public Builder managerId(Integer managerId) { this.managerId = managerId; return this; }
        public Employee build() { return new Employee(this); }
    }
}
