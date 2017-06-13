package com.mappingobjects.domain.dto;

import java.util.HashSet;
import java.util.Set;

public class ManagerDto {

    private String firstName;

    private String lastName;

    private Set<EmployeeDto> employeesDtos;

    public ManagerDto() {
        this.setEmployeesDtos(new HashSet<>());
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

    public Set<EmployeeDto> getEmployeesDtos() {
        return employeesDtos;
    }

    public void setEmployeesDtos(Set<EmployeeDto> employeesDtos) {
        this.employeesDtos = employeesDtos;
    }

    public void addEmployeeDto(EmployeeDto employeeDto){
        this.getEmployeesDtos().add(employeeDto);
    }
}
