package edu.javacourse.tomcat.dao;

import edu.javacourse.tomcat.business.Book;
import edu.javacourse.tomcat.business.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;
    private final BookDao bookDao;
    @Autowired
    public PersonDAO(SessionFactory sessionFactory,BookDao bookDao){
        this.bookDao=bookDao;
        this.sessionFactory=sessionFactory;
    }
    @Transactional
    public Person getOnePerson(int id){
         Session session=sessionFactory.getCurrentSession();
         return session.get(Person.class,id);
    }
    public boolean checkByEmail(String email){
        return false;
    }
    public boolean checkByName(String name){
        return false;
    }
    @Transactional(readOnly=true)
    public List<Person>index() throws SQLException {
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }
    @Transactional
    public void save(Person person)  {
           Session session=sessionFactory.getCurrentSession();
           session.save(person);
    }
    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        Session session=sessionFactory.getCurrentSession();
        session.update(person);
    }
    @Transactional
    public void remove(int id) {
          Session session=sessionFactory.getCurrentSession();
          Person person=session.get(Person.class,id);
          session.remove(person);
    }
    public List<Book>getBooksByPersonID(int personId){
        return null;
    }
    public void testMultipleUpdate() {

    }

    public void testBatchUpdate(){

    }
    public List<Person> peopleGenerator() {
        return null;
    }
}
