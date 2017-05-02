package _02_jdbc_advanced.mini_hibernate.models;


import _02_jdbc_advanced.mini_hibernate.persistance.Column;
import _02_jdbc_advanced.mini_hibernate.persistance.Entity;
import _02_jdbc_advanced.mini_hibernate.persistance.Id;

import java.util.Date;

@Entity(name = "users")
public class User {

    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "registration_date")
    private Date registrationDate;

    @SuppressWarnings("unused")
    public User() {
        super();
    }

    public User(String name, int age, Date registrationDate) {
        this.setName(name);
        this.setAge(age);
        this.setRegistrationDate(registrationDate);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: " + this.getId()).append(System.lineSeparator());
        sb.append("Name: " + this.getName()).append(System.lineSeparator());
        sb.append("Age: " + this.getAge()).append(System.lineSeparator());
        sb.append("Registration Date: " + this.getRegistrationDate());

        return sb.toString();
    }
}
