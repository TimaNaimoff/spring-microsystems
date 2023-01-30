package edu.javacourse.tomcat.repo;

import edu.javacourse.tomcat.business.Book;
import edu.javacourse.tomcat.business.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book,Integer> {
    List<Book> findByAuthorName(String name);

    List<Book> findByPerson(Person person);


}
