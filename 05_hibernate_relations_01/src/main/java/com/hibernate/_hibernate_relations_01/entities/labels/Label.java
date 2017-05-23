package com.hibernate._hibernate_relations_01.entities.labels;

import java.io.Serializable;

public interface Label extends Serializable{

    long getId();

    void setId(long id);

    String getTitle();

    void setTitle(String title);

    String getSubtitle();

    void setSubtitle(String subTitle);

}
