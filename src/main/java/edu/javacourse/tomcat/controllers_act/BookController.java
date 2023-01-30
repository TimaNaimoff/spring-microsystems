package edu.javacourse.tomcat.controllers_act;

import edu.javacourse.tomcat.business.Book;
import edu.javacourse.tomcat.business.Person;
import edu.javacourse.tomcat.services.BookService;
import edu.javacourse.tomcat.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;
    @Autowired
    public BookController(BookService bookService, PeopleService peopleService){
        this.bookService=bookService;
        this.peopleService=peopleService;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("books",bookService.findAll());
        return "/book/books";

    }
    @GetMapping("/new")
    public String addNewBookGet(Model model){
        model.addAttribute("book",new Book());
        return "/book/new";
    }
    @PostMapping
    public String addNewBook(@ModelAttribute("book") Book book){
         bookService.save(book);
         return "redirect:/books";
     }
     @GetMapping("/{id}/update")
     public String getUpd(@PathVariable("id")int counter,@ModelAttribute("book")Book book){
        return "book/edit";
     }
//     @PatchMapping("/{id}/update")
//     public String referBook(@ModelAttribute("person")Person person,@PathVariable("id")int bookID){
//        bookDao.addRef(person,bookID);
//        return "redirect:/books";
//     }
     @PatchMapping("/{id}")
     public String updateBook(@ModelAttribute("book")Book book,@PathVariable("id")int id){
        bookService.update(id,book);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String show(@ModelAttribute("person")Person personny,@PathVariable("id")int id,Model model) throws SQLException {
        Book book=bookService.getOneBook(id);
        Person person=peopleService.findOnePerson(id);
        Map<String,Object>map=new HashMap<>();
        map.put("book",book);
        if(person==null){
            map.put("personi",peopleService.findAll());
            model.addAllAttributes(map);
            return "/book/show2";
        }
        map.put("person",person);
        model.addAllAttributes(map);
        return "/book/show";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id")int id){
        bookService.delete(id);
        return "redirect:/books";
    }
//    @DeleteMapping("/{id}/del")
//    public String dropBook(@PathVariable("id")int id){
//        bookDao.deleteRef(id);
//        return "redirect:/books";
//    }
}
