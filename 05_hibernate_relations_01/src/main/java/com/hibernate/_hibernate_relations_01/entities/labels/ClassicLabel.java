package com.hibernate._hibernate_relations_01.entities.labels;


import com.hibernate._hibernate_relations_01.entities.shampoos.BasicShampoo;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class ClassicLabel implements Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String title;

    @Basic
    private String subtitle;

    //if we want in the labels to have a two way connection with BasicShampoo
    @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class)
    private BasicShampoo basicShampoo;

    public ClassicLabel() {
    }

    public ClassicLabel(String title, String subtitle) {
        this.setTitle(title);
        this.setSubtitle(subtitle);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getSubtitle() {
        return subtitle;
    }

    @Override
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public BasicShampoo getBasicShampoo() {
        return basicShampoo;
    }

    public void setBasicShampoo(BasicShampoo basicShampoo) {
        this.basicShampoo = basicShampoo;
    }
}
