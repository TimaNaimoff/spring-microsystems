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
        list.add(new Person(++PEOPLE_COUNT,"Kolya",18,"kolyan09@gmail.com"));
        list.add(new Person(++PEOPLE_COUNT,"Stephan",21,"steffy@gmail.com"));
        list.add(new Person(++PEOPLE_COUNT,"Pjujeck",25,"hasulandria@gmail.com"));
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

    public void update(int id, Person person) {
        Person personter=getOnePerson(id);
        personter.setName(person.getName());
        personter.setAge(person.getAge());
        personter.setEmail(person.getEmail());
    }

    public void remove(int id) {
        list.removeIf(e->e.getId()==id);
    }
}
