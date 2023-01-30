package edu.javacourse.tomcat.repo;

import edu.javacourse.tomcat.business.Book;
import edu.javacourse.tomcat.business.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);

    List<Person> findByNameOrderByAge(String name);

    List<Person> findByEmail(String name);

    List<Person> findByNameStartingWith(String startingWith);

    List<Person> findByNameOrEmail(String name,String email);
}
