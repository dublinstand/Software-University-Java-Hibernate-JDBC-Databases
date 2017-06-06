package app.domain.dto;

import com.google.gson.annotations.Expose;

public class CategoryDto {

    @Expose
    private String name;

    public CategoryDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
