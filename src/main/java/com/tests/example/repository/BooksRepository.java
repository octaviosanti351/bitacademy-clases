package com.tests.example.repository;


import com.tests.example.model.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BooksRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BooksRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private String GET_BOOKS = "select * from public.book";

    public List<Book> getBooks() {
        return jdbcTemplate.query(GET_BOOKS, BeanPropertyRowMapper.newInstance(Book.class));
    }
}
