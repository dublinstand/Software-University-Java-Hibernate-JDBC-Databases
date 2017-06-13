package com.jsonexercise.domain.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class CategoryDto {

    @Expose
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
