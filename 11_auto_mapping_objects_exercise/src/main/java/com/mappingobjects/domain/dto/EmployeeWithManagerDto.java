package com.mappingobjects.domain.dto;

import java.math.BigDecimal;

public class EmployeeWithManagerDto {

    private String firstName;

    private String lastName;

    private BigDecimal salary;

    //goes to Employee.class, finds field Manager (which is an Employee.class) and gets Last Name - naming is very important to map the property
    private String employeeManagerLastName;

    public EmployeeWithManagerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getEmployeeManagerLastName() {
        return employeeManagerLastName;
    }

    public void setEmployeeManagerLastName(String employeeManagerLastName) {
        this.employeeManagerLastName = employeeManagerLastName;
    }

    @Override
    public String toString() {
        return "EmployeeWithManagerDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", employeeManagerLastName='" + employeeManagerLastName + '\'' +
                '}';
    }
}
