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
    private static final String SELECT_ONE_BOOK_PER="SELECT p.name,p.age,p.person_id,p.email FROM book b LEFT JOIN person p ON p.person_id=b.person_id WHERE b.person_id = ?";
    private static final String SELECT_ONE_BOOK="" +
            "SELECT * FROM book WHERE book_id = ?";
    private static final String INSERT_BOOK = "INSERT INTO book(person_id,book_name, book_author," +
            "book_year)VALUES(null, ? , ? , ?)";
    private static final String UPDATE_BOOK="UPDATE book SET book_name=? ,book_author = ? ," +
            " book_year=? WHERE book_id = ?";
    private static final String DELETE_BOOK="DELETE FROM book WHERE book_id = ?";
    private static final String DELETE_REF_BOOK_PERSON="UPDATE book SET person_id = null WHERE book_id = ?";
    private static final String ADD_BOOK_TO_PERSON="UPDATE book SET person_id = ? WHERE book_id = ?";
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
    public Person getPersoner(int id){
        return template.query(SELECT_ONE_BOOK_PER,new Object[]{id},new PersonMapper())
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
    public void deleteRef(int id){
        template.update(DELETE_REF_BOOK_PERSON,id);
    }
    public void addRef(int personID,int bookID){
        template.update(ADD_BOOK_TO_PERSON,personID,bookID);
    }




}
