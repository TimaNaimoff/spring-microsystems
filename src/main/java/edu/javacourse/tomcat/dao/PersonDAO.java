package edu.javacourse.tomcat.dao;

import edu.javacourse.tomcat.business.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
            "INSERT INTO person(name,age,email,address) VALUES(?,?,?,?)";
    private static final String UPDATE_PERSON=
            "UPDATE person SET name = ?, age = ? , email = ? , address = ? WHERE person_id = ?";
    private static final String DELETE_PERSON=
            "DELETE FROM person WHERE person_id = ?";
    private static final String SELECT_BY_EMAIL="SELECT * FROM person WHERE" +
            " email LIKE ?";


    public Person getOnePerson(int id){
        return template.query(SELECT_ONE_PERSON,new Object[]{id},new PersonMapper())
                .stream().findAny().orElse(null);
    }
    public boolean checkByEmail(String email){
        return template.query(SELECT_BY_EMAIL,new Object[]{email},new PersonMapper()).size()!=0;
    }
    public List<Person>index() throws SQLException {
        return template.query(SELECT_ALL_PEOPLES,new PersonMapper());
    }

    public void save(Person person)  {
        template.update(ADD_ONE_PERSON,person.getName(),person.getAge(),person.getEmail(),person.getAddress());
    }

    public void update(int id, Person person) {
         template.update(UPDATE_PERSON,person.getName(),person.getAge(),person.getEmail(),person.getAddress(),id);

    }
;
    public void remove(int id) {
        template.update(DELETE_PERSON,id);
    }

    public void testMultipleUpdate() {
        List<Person>people=new ArrayList<>();
        long timer=System.currentTimeMillis();

        template.batchUpdate(ADD_ONE_PERSON, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                     preparedStatement.setString(1,people.get(i).getName());
                     preparedStatement.setInt(2,people.get(i).getAge());
                     preparedStatement.setString(3,people.get(i).getEmail());
                }

                @Override
                public int getBatchSize() {
                    return people.size();
                }
            });
        System.out.println("With batch:"+(System.currentTimeMillis()-timer));

    }

    public void testBatchUpdate(){
        List<Person> people = peopleGenerator();
        long timer=System.currentTimeMillis();
        for(Person p:people){
            template.update(ADD_ONE_PERSON,p.getName(),p.getAge(),p.getEmail());
        }
        System.out.println("With batch:"+(System.currentTimeMillis()-timer));

    }
    public List<Person> peopleGenerator() {
        List<Person>people=new ArrayList<>();
        int counter=0;
        while(counter<1001) {
            people.add(new Person("Tikr"+counter,counter,"aawpe"+counter+"@gmail.com","abcda"));
            counter++;
        }
        return people;
    }
}
