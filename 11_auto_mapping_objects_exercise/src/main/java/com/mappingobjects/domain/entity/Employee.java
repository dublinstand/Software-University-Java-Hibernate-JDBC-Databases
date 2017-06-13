package com.mappingobjects.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private BigDecimal salary;

    @Basic
    private String address;

    //we want to create a manager for all employees, and the manager is still employee. One manager can have a lot of employees
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private Set<Employee> managerEmployees;

    public Employee() {
        this.setManagerEmployees(new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Employee> getManagerEmployees() {
        return managerEmployees;
    }

    public void setManagerEmployees(Set<Employee> managerEmployees) {
        this.managerEmployees = managerEmployees;
    }

    public void addEmployee(Employee employee){
        this.getManagerEmployees().add(employee);
    }
}
