package edu.javacourse.tomcat.dao;


import edu.javacourse.tomcat.business.Person;
import edu.javacourse.tomcat.repo.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonerDao {
    private PeopleRepository peopleRepository;
    @Autowired
    public PersonerDao(PeopleRepository peopleRepository){
        this.peopleRepository=peopleRepository;
    }

    @Transactional
    public void testPaginSort(){
        Page<Person>page=peopleRepository.findAll(PageRequest.of(1,10));
        System.out.println(page.getTotalElements());
        List<Person>personers=peopleRepository.findAll(Sort.by("age"));
    }
    @Transactional(readOnly=true)
    public void testNPlus1(){
//         List<Person> pp=peopleRepository.getPersons();
//         for(Person p:pp){
//             System.out.println(p.getList());
//         }
        List<Person>pp=peopleRepository.getPersonsJoin();
        Set<Person> set=new HashSet<>(pp);
        for(Person p:pp){
            System.out.println(p.getName()+":"+p.getList());
        }
     }
}
