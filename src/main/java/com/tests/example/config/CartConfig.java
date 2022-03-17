package com.tests.example.config;

import com.tests.example.model.Book;
import com.tests.example.business.Cart;
import com.tests.example.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class CartConfig {

    @Autowired
    BooksRepository booksRepository;

    @Bean
    public Cart cartSession(){
        List<Book> books = booksRepository.getBooks();
        Map<Object, Double> catalog = new HashMap<>();
        books.forEach(b -> catalog.put(b.getIsbn(), b.getPrice()));
        return new Cart(catalog);
    }
}
