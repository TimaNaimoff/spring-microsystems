package edu.javacourse.tomcat.controllers_act;

import edu.javacourse.tomcat.business.Book;
import edu.javacourse.tomcat.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao){
        this.bookDao=bookDao;
    }
    @GetMapping()
    public String index(){
        return "book/books";
    }
    @PostMapping("/new")
    public String addNewBook(@ModelAttribute("book") Book book){
         bookDao.insert(book);
         return "redirect:/books";
     }
     @GetMapping("{id}/update")
     public String getUpd(){
        return "book/edit";
     }
     @PatchMapping("/{id}")
     public String updateBook(@ModelAttribute("book")Book book,@PathVariable("id")int id){
        bookDao.update(id,book);
        return "redirect:/books";
    }
    @DeleteMapping("{/id}")
    public String deleteBook(@PathVariable("id")int id){
        bookDao.delete(id);
        return "redirect:/books";
    }
}
