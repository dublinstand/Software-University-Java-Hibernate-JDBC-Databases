package app.serviceimpl;

import app.domain.dto.CategoryDto;
import app.domain.model.Category;
import app.domain.model.Product;
import app.repository.CategoryRepository;
import app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void create(CategoryDto categoryDto, Product product) {
        Category category = this.convertToEntity(categoryDto);
        category.getProducts().add(product);
        this.categoryRepository.saveAndFlush(category);
    }

    private CategoryDto convertToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());

        return categoryDto;
    }

    private Category convertToEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());

        return category;
    }
}
