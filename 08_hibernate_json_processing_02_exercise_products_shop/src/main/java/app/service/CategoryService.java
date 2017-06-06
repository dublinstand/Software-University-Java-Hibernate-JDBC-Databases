package app.service;

import app.domain.dto.CategoryDto;
import app.domain.model.Product;

public interface CategoryService {

    void create(CategoryDto categoryDto, Product product);

}
