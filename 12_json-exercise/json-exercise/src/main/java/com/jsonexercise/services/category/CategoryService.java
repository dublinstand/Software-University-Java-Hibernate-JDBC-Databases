package com.jsonexercise.services.category;

import com.jsonexercise.domain.dto.CategoryDto;
import com.jsonexercise.domain.dto.CategoryStatsDto;

import java.util.List;

public interface CategoryService {

    void create(CategoryDto categoryDto);

    List<CategoryStatsDto> getCategoryStats();
}