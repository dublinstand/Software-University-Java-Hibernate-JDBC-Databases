package com.jsonexercise.domain.dto;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class SellerDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    Set<ProductSellerDto> soldProducts;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ProductSellerDto> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<ProductSellerDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
