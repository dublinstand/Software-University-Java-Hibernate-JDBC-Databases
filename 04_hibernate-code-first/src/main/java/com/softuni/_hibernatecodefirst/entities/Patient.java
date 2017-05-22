package com.softuni._hibernatecodefirst.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "picture", columnDefinition = "BLOB")
    private byte[] picture;

    @Column(name = "has_medical_insurance")
    private Boolean hasMedicalInsurance;

    //we get this set mapped to Visitation class, by property patients in Visitation. We map the entity to Visitation.class
    //you need to add @JoinColumn at patients in Visitation class and @ManyToOne
    //one patient has many visitations
    @OneToMany(mappedBy = "patient", targetEntity = Visitation.class)
    Set<Visitation> visitations;

    @OneToMany(mappedBy = "patient", targetEntity = Diagnose.class)
    Set<Diagnose> diagnoses;

    //we have a connection many to many
    @ManyToMany(mappedBy = "patients", targetEntity = Medicament.class)
    Set<Medicament> medicament;

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<Medicament> getMedicament() {
        return medicament;
    }

    public void setMedicament(Set<Medicament> medicament) {
        this.medicament = medicament;
    }

    public Boolean getHasMedicalInsurance() {
        return hasMedicalInsurance;
    }

    public void setHasMedicalInsurance(Boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }
}
