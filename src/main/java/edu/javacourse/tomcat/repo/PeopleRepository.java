package edu.javacourse.tomcat.repo;

import edu.javacourse.tomcat.business.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
