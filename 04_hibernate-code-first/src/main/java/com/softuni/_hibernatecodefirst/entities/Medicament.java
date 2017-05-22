package com.softuni._hibernatecodefirst.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    //because many medicaments can be assigned to many patients, we have many to many link
    //we use JoinTable and have a new table that joins the two values as medicaments_patients.
    //the first column is referenced by the id from Medicament table, the second column is inverseJoinColumns and id for the Patient class
    //"medicament_id" and "patient_id" will be the names of the database of the constrained foreign key
    @ManyToMany
    @JoinTable(name = "medicaments_patients",
            joinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"))
    private Set<Patient> patients;

    public Medicament() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
