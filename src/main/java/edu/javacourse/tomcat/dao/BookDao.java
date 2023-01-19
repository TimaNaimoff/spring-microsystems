package edu.javacourse.tomcat.dao;

import edu.javacourse.tomcat.business.Book;
import edu.javacourse.tomcat.business.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate template;
    private static final String SELECT_ALL="SELECT * FROM book";
    private static final String SELECT_ONE_BOOK_PER="SELECT * FROM book b" +
            " LEFT JOIN person p ON p.person_id=b.person.id";
    private static final String SELECT_ONE_BOOK="" +
            "SELECT * FROM book WHERE book_id = ?";
    private static final String INSERT_BOOK = "INSERT INTO book(book_name, book_author" +
            "book_year)VALUES(? , ? , ?)";
    private static final String UPDATE_BOOK="UPDATE book SET book_name=? ,book_author = ? ," +
            " book_year=? WHERE book_id = ?";
    private static final String DELETE_BOOK="DELETE FROM book WHERE book_id = ?";
    @Autowired
    public BookDao(JdbcTemplate template){
        this.template=template;
    }

    public List<Book> selectAllBooks(){
        return template.query(SELECT_ALL,new BookMapper());
    }
    public Book getBook(int id){
        return template.query(SELECT_ONE_BOOK,new Object[]{id},new BookMapper())
                .stream().findAny().orElse(null);
    }
    public Person getPersoner(){
        return template.query(SELECT_ONE_BOOK_PER,new PersonMapper())
                .stream().findAny().orElse(null);
    }
    public void insert(Book book){
        template.update(INSERT_BOOK,book.getTitle(),book.getAuthorName(),book.getYear());
    }
    public void update(int id, Book book) {
        template.update(UPDATE_BOOK,book.getTitle(),book.getAuthorName(),book.getYear(),id);
    }
    public void delete(int id){
        template.update(DELETE_BOOK,id);
    }



}
