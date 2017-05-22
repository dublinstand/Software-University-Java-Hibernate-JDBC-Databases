package com.softuni._hibernatecodefirst.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "visitations")
public class Visitation implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    //have a constraint with the Patient table that reference "id" from Patient as constraint column.
    //patient_id is the column in the database that will be created, it will be constrained in the Patient class with column in the Patient class “id”ManyToOne
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @OneToMany(mappedBy = "visitation", targetEntity = Comment.class)
    private List<Comment> comments;

    public Visitation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
