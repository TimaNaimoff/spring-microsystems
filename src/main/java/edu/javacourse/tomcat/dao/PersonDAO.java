package edu.javacourse.tomcat.dao;

import edu.javacourse.tomcat.business.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static long PEOPLE_COUNT;
    private List<Person> list;
    {
        list=new ArrayList<>();
        list.add(new Person(++PEOPLE_COUNT,"Kolya"));
        list.add(new Person(++PEOPLE_COUNT,"Stephan"));
        list.add(new Person(++PEOPLE_COUNT,"Pjujeck"));
    }

    public List<Person> getList() {
        return list;
    }
    public Person getOnePerson(int id){
        return list.stream().filter(e->e.getId()==(long)id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        list.add(person);
    }
}
