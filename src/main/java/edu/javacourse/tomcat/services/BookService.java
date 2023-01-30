package edu.javacourse.tomcat.services;

import edu.javacourse.tomcat.business.Book;
import edu.javacourse.tomcat.business.Person;
import edu.javacourse.tomcat.repo.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly=true)
public class BookService {
    private final BooksRepository booksRepository;
    @Autowired
    public BookService(BooksRepository booksRepository){
        this.booksRepository=booksRepository;
    }
    public List<Book> findAll(){
        return booksRepository.findAll();
    }
    public Book getOneBook(int id){
        return booksRepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }
    @Transactional
    public void update(int id,Book book){
        Book booker=new Book();
        booker.setId(id);
        booker.setPerson(book.getPerson());
        booker.setYear(book.getYear());
        booker.setAuthorName(book.getAuthorName());
        booker.setPerson(book.getPerson());
        booksRepository.save(booker);
    }
    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    public List<Book>findByAuthorName(String itemName){
        return booksRepository.findByAuthorName(itemName);
    }
    public List<Book>findByPerson(Person person){
        return booksRepository.findByPerson(person);
    }


}
