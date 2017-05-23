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
    @OneToOne(mappedBy = "classicLabel", targetEntity = BasicShampoo.class)
    private BasicShampoo basicShampoo;

    public ClassicLabel() {
    }

    public ClassicLabel(String title, String subtitle) {
        this.setTitle(title);
        this.setSubtitle(subtitle);
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long id) {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public String getSubtitle() {
        return null;
    }

    @Override
    public void setSubtitle(String subTitle) {

    }
}
