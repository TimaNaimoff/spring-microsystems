package edu.javacourse.tomcat.dao;

import edu.javacourse.tomcat.business.Book;
import edu.javacourse.tomcat.business.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookDao {
    private SessionFactory sessionFactory;
    @Autowired
    public BookDao(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Transactional
    public List<Book> selectAllBooks(){
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery("SELECT b FROM Book b",Book.class).getResultList();
    }
    @Transactional
    public Book getBook(int id){
        Session session= sessionFactory.getCurrentSession();
        return session.get(Book.class,id);
    }
    @Transactional
    public Person getPersoner(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class,id).getPerson();
    }
    @Transactional
    public void insert(Book book){
         Session session=sessionFactory.getCurrentSession();
         session.save(book);
    }
    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        Session session=sessionFactory.getCurrentSession();
        session.update(book);
    }
    @Transactional
    public void delete(int id){
        Session session=sessionFactory.getCurrentSession();
        Book book=session.get(Book.class,id);
        session.remove(book);
    }
    @Transactional
    public void deleteRef(int id){
         Session session=sessionFactory.getCurrentSession();
         Person person=session.get(Person.class,id);
         List<Book>books=person.getList();
         books.forEach(e->{
             e.setPerson(null);
         });
    }
    @Transactional
    public void addRef(Person person,int bookID){
          Session session=sessionFactory.getCurrentSession();
          Hibernate.initialize(person);
          Book book=session.get(Book.class,bookID);
          person.getList().add(book);
    }
}
