package edu.javacourse.tomcat.dao;

import edu.javacourse.tomcat.business.Book;
import edu.javacourse.tomcat.business.Person;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {
    private SessionFactory sessionFactory;
    @Autowired
    public BookDao(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public List<Book> selectAllBooks(){
         return null;
    }
    public Book getBook(int id){
        return null;
    }
    public Person getPersoner(int id){
        return null;
    }
    public void insert(Book book){

    }
    public void update(int id, Book book) {
    }
    public void delete(int id){

    }
    public void deleteRef(int id){

    }
    public void addRef(Person person,int bookID){

    }
}
