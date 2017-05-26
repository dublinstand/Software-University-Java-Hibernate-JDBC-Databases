package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.terminal;


import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Author;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Book;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Category;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.enums.AgeRestriction;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.enums.EditionType;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.service.AuthorService;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.service.BookService;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class Terminal implements CommandLineRunner{


    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @Autowired
    CategoryService categoryService;


    @Override
    public void run(String... strings) throws Exception {
        seedDatabase();
    }

    //Write a program that print titles of all books where their age restriction matches the given input (minor, teen or adult). Ignore casing of the input.
    private void printTitlesWithGivenAgeRestriction() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (!(input = bufferedReader.readLine()).equals("Stop")){
            AgeRestriction ageRestriction = AgeRestriction.valueOf(input.toUpperCase());
            List<Book> books = this.bookService.findByAgeRestriction(ageRestriction);
            for (Book book : books) {
                System.out.println(book.getTitle());
            }
        }
    }


    private void seedDatabase() throws IOException, ParseException {
        //read data from file for authors and store them
        BufferedReader authorsReader = new BufferedReader(new FileReader("resources\\authors.txt"));
        //miss the first line
        String line = authorsReader.readLine();

        List<Author> authors = new ArrayList<>();
        List<Category> categories = new ArrayList<>();

        //while the line is not empty
        while ((line = authorsReader.readLine()) != null){

            //split by empty space
            String[] data = line.split("\\s+");
            String firstName = data[0];
            String lastName = data[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            this.authorService.create(author);
            authors.add(author);
        }

        //read data from file for categories and store them
        BufferedReader categoriesReader = new BufferedReader(new FileReader("resources\\categories.txt"));

        while ((line = categoriesReader.readLine()) != null){
            Category category = new Category();
            category.setName(line);
            this.categoryService.create(category);
            categories.add(category);
        }

        //read data from file for books and store them
        BufferedReader booksReader = new BufferedReader(new FileReader("resources\\books.txt"));

        //miss the first line
        line = booksReader.readLine();

        Random random = new Random();

        while ((line = booksReader.readLine()) != null){
            //split by empty space
            String[] data = line.split("\\s+");

            //random index of authors that we have added to the list in the while loop
            int authorIndex = random.nextInt(authors.size());
            //get random author from the list
            Author author = authors.get(authorIndex);

            //get the value based on the numbers (0,1,2) passed from the file and take the ENUM value of it
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];

            //create a simple date format and parse the data from the file
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);

            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];

            //build the title because we separate the new line by spaces
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();


            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);

            int categoryIndex = random.nextInt(categories.size());
            Category category = categories.get(categoryIndex);

            book.addCategory(category);

            this.bookService.create(book);
        }
    }
}
