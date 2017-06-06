package app.terminal;

import app.domain.dto.CategoryDto;
import app.domain.dto.ProductDto;
import app.domain.dto.UserDto;
import app.domain.model.Product;
import app.domain.model.User;
import app.io.JSONParser;
import app.service.CategoryService;
import app.service.ProductService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Component
public class Terminal implements CommandLineRunner{

    @Autowired
    private JSONParser jsonParser;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... strings) throws Exception {
//        this.seedUsersFromJsonFile();
//
//        List<User> users = this.userService.findAll();
//        this.seedProductsFromJsonFileAndUseRandomUser(users);
//
//        List<Product> products = this.productService.findAll();
//        this.seedCategoriesFromJsonFileAndUseRandomCategory(products);

//        List<ProductDto> productsInRange = this.productService.getProductsBetween500And1000WithNoBuyer();
//        this.writeManyProductsToJson(productsInRange);

        List<UserDto> usersWithSoldProducts = this.userService.findUserWithSoldProductsChangeToDto();
        this.writeUsersToJson(usersWithSoldProducts);
    }

    private void seedUsersFromJsonFile(){
        try {
            UserDto[] userDtos = this.jsonParser.read(UserDto[].class, "src/main/resources/files/input/json/users.json");
            for (UserDto userDto : userDtos) {
                this.userService.create(userDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void seedProductsFromJsonFileAndUseRandomUser(List<User> users){
        int userCount = users.size();
        Random random = new Random();

        try {
            ProductDto[] productDtos = this.jsonParser.read(ProductDto[].class, "src/main/resources/files/input/json/products.json");

            for (int i = 0; i < 33; i++) {
                ProductDto productDto = productDtos[i];
                User seller = users.get(random.nextInt(userCount - 1));
                this.productService.createWithNoBuyer(productDto, seller);
            }

            for (int i = 33; i < productDtos.length; i++) {
                ProductDto productDto = productDtos[i];
                User buyer = users.get(random.nextInt(userCount - 1));
                User seller = users.get(random.nextInt(userCount - 1));

                this.productService.create(productDto, buyer, seller);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void seedCategoriesFromJsonFileAndUseRandomCategory(List<Product> products) {
        int productsCount = products.size();
        Random random = new Random();

        try {
            CategoryDto[] categoryDtos = this.jsonParser.read(CategoryDto[].class, "src/main/resources/files/input/json/categories.json");

            for (CategoryDto categoryDto : categoryDtos) {
                Product product = products.get(random.nextInt(productsCount - 1));

                this.categoryService.create(categoryDto, product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeManyProductsToJson(List<ProductDto> productDtos){
        try {
            this.jsonParser.write(productDtos, "src/main/resources/files/output/json/products.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeUsersToJson(List<UserDto> userDtos){
        try {
            this.jsonParser.write(userDtos, "src/main/resources/files/output/json/users.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
