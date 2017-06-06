package app.domain.dto;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

public class UserDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Integer age;

    @Expose
    private Set<ProductDto> soldProducts;

    public UserDto() {
        this.setSoldProducts(new HashSet<>());
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<ProductDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductDto> soldProducts) {
        this.soldProducts = soldProducts;
    }


}
