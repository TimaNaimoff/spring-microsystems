package edu.javacourse.tomcat.dao;

import edu.javacourse.tomcat.business.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book=new Book();
        book.setTitle(resultSet.getString("book_name"));
        book.setAuthorName(resultSet.getString("book_author"));
        book.setYear(resultSet.getInt("book_year"));
        book.setId(resultSet.getInt("book_id"));
        return book;
    }
}
