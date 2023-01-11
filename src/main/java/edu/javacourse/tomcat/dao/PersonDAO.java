package edu.javacourse.tomcat.dao;

import edu.javacourse.tomcat.business.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    @Autowired
    private final JdbcTemplate template;
    public PersonDAO(JdbcTemplate template){
        this.template=template;
    }
    private static final String SELECT_ALL_PEOPLES = "SELECT * FROM person";
    private static final String SELECT_ONE_PERSON =
            "SELECT * FROM person WHERE person_id = ?";
    private static final String ADD_ONE_PERSON =
            "INSERT INTO person(name,age,email) VALUES(?,?,?)";
    private static final String UPDATE_PERSON=
            "UPDATE person SET name = ?, age = ? , email = ? WHERE person_id = ?";
    private static final String DELETE_PERSON=
            "DELETE FROM person WHERE person_id = ?";


    public Person getOnePerson(int id){
        return template.query(SELECT_ONE_PERSON,new Object[]{id},new PersonMapper())
                .stream().findAny().orElse(null);
    }
    public List<Person>index() throws SQLException {
        return template.query(SELECT_ALL_PEOPLES,new PersonMapper());
    }

    public void save(Person person)  {
        template.update(ADD_ONE_PERSON,person.getName(),person.getAge(),person.getEmail());
    }

    public void update(int id, Person person) {
         template.update(UPDATE_PERSON,person.getName(),person.getAge(),person.getEmail(),id);

    }
;
    public void remove(int id) {
        template.update(DELETE_PERSON,id);
    }
}
