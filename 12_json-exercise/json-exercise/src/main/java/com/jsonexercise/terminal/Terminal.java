package com.jsonexercise.terminal;

import com.jsonexercise.domain.dto.*;
import com.jsonexercise.parser.JsonParser;
import com.jsonexercise.services.category.CategoryService;
import com.jsonexercise.services.product.ProductService;
import com.jsonexercise.services.user.UserService;
import com.jsonexercise.validator.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private JsonParser jsonParser;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DtoValidator dtoValidator;

    @Override
    public void run(String... strings) throws Exception {
//        this.parseJsonCategories();
//        this.parseJsonUsers();
//        this.parseJsonProducts();
        this.exportBuyerDtos();
        this.exportSellerDtos();
        this.exportCategoryStats();
    }

    private void parseJsonCategories() {
        CategoryDto[] categoryDtos = null;
        try {
            categoryDtos = this.jsonParser.importJson(CategoryDto[].class, "/files/input/categories.json");
//            for (CategoryDto categoryDto : categoryDtos) {
//                if (this.dtoValidator.isValid(categoryDto)) {
//                    throw new IllegalArgumentException("Invalid");
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (CategoryDto categoryDto : categoryDtos) {
            try {
                this.categoryService.create(categoryDto);
            } catch (Exception e){
                System.out.println("Invalid Category");
            }
        }

    }

    private void parseJsonUsers() {
        UserDto[] userDtos = null;
        try {
            userDtos = this.jsonParser.importJson(UserDto[].class, "/files/input/users.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (UserDto userDto : userDtos) {
            try {
                this.userService.create(userDto);
            } catch (Exception e){
                System.out.println("Invalid User");
            }
        }
    }

    private void parseJsonProducts(){
        ProductDto[] productDtos = null;
        try {
            productDtos = this.jsonParser.importJson(ProductDto[].class, "/files/input/products.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (ProductDto productDto : productDtos) {
            try {
                this.productService.create(productDto);
            } catch (Exception e){
                System.out.println("Invalid Product");
            }
        }
    }

    private void exportBuyerDtos(){
        List<ProductBuyerDto> productBuyerDtoList = this.productService.findWithoutBuyer();
        try {
            this.jsonParser.exportJson(productBuyerDtoList, "src/main/resources/files/output/buyers.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportSellerDtos(){
        List<SellerDto> sellerDtos = this.userService.findWithMoreThanOneBuyer();
        try {
            this.jsonParser.exportJson(sellerDtos, "src/main/resources/files/output/sellers.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportCategoryStats(){
        List<CategoryStatsDto> categoryStatsDtos = this.categoryService.getCategoryStats();
        try {
            this.jsonParser.exportJson(categoryStatsDtos,"src/main/resources/files/output/categories.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
