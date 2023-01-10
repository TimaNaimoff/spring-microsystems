package edu.javacourse.tomcat.dao;

import edu.javacourse.tomcat.business.Person;
import edu.javacourse.tomcat.connection.ConnectionDb;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static  Connection connection;
    private static final String SELECT_ALL_PEOPLES = "SELECT * from person";
    private static final String ADD_ONE_PERSON =
            "INSERT INTO person(name,age,email) VALUES(?,?,?)";
    static {
        try {
            connection = ConnectionDb.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Person getOnePerson(int id){
        return new Person();
    }
    public List<Person>index() throws SQLException {
    try(
        Statement statement=connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_PEOPLES);
    ) {
        List<Person> list = new ArrayList<>();

        while (resultSet.next()) {
            Person person = new Person();
            person.setId(resultSet.getLong("person_id"));
            person.setAge(resultSet.getInt("age"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
            list.add(person);
        }
        return list;
    }
    }
    public void save(Person person)  {
        try(
                PreparedStatement statement=connection.prepareStatement(ADD_ONE_PERSON);
        ){
            statement.setString(1,person.getName());
            statement.setInt(2,person.getAge());
            statement.setString(3,person.getEmail());
            ResultSet set=statement.executeQuery();
            System.out.println(set.getFetchSize());
            set.close();
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person person) {
//        Person personter=getOnePerson(id);
//        personter.setName(person.getName());
//        personter.setAge(person.getAge());
//        personter.setEmail(person.getEmail());
    }
//
//    public void remove(int id) {
//        list.removeIf(e->e.getId()==id);
//    }
}
