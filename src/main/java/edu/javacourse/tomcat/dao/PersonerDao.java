package edu.javacourse.tomcat.dao;


import edu.javacourse.tomcat.business.Person;
import edu.javacourse.tomcat.repo.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonerDao {
    private PeopleRepository peopleRepository;
    @Autowired
    public PersonerDao(PeopleRepository peopleRepository){
        this.peopleRepository=peopleRepository;
    }
    @Transactional(readOnly=true)
    public void testNPlus1(){
         List<Person> pp=peopleRepository.getPersons();
         for(Person p:pp){
             System.out.println(p.getList());
         }
     }
}
