package com.jsonexercise.services.category;

import com.jsonexercise.domain.dto.CategoryDto;
import com.jsonexercise.domain.dto.CategoryStatsDto;
import com.jsonexercise.domain.entities.Category;
import com.jsonexercise.parser.ModelParser;
import com.jsonexercise.repositories.CategoryRepository;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelParser modelParser;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelParser modelParser) {
        this.categoryRepository = categoryRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(CategoryDto categoriesDto) {
        Category category = this.modelParser.convert(categoriesDto, Category.class);
            this.categoryRepository.save(category);
    }

    @Override
    public List<CategoryStatsDto> getCategoryStats() {
        List<Object[]> data = this.categoryRepository.getCategoryStats();
//        PropertyMap<Object[], CategoryStatsDto> propertyMap = new PropertyMap<Object[], CategoryStatsDto>() {
//            @Override
//            protected void configure() {
//                map(String.valueOf(source[0]), destination.getName());
//                map((Long)source[1], destination.getProductsCount());
//                map((Double) source[2], destination.getAveragePrice());
//                map((Double) source[3], destination.getTotalRevenue());
//            }
//        };
        List<CategoryStatsDto> categoryStatsDtos = new ArrayList<>();
        for (Object[] objects : data) {
            CategoryStatsDto categoryStatsDto= new CategoryStatsDto();
            categoryStatsDto.setName(String.valueOf(objects[0]));
            categoryStatsDto.setProductsCount((long) objects[1]);
            categoryStatsDto.setAveragePrice((double) objects[2]);
            categoryStatsDto.setTotalRevenue((BigDecimal) objects[3]);
            categoryStatsDtos.add(categoryStatsDto);
        }

        //List<CategoryStatsDto> categoryStatsDtos = this.modelParser.convert(data, CategoryStatsDto.class, propertyMap);
        return categoryStatsDtos;
    }
}