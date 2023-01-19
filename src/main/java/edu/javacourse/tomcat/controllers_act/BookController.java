package edu.javacourse.tomcat.controllers_act;

import edu.javacourse.tomcat.business.Book;
import edu.javacourse.tomcat.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String index(Model model){
        model.addAttribute("books",bookDao.selectAllBooks());
        return "book/books";

    }
    @PostMapping("/new")
    public String addNewBook(@ModelAttribute("book") Book book){
         bookDao.insert(book);
         return "redirect:/books";
     }
     @GetMapping("/{id}/update")
     public String getUpd(){
        return "book/edit";
     }
     @PatchMapping("/{id}")
     public String updateBook(@ModelAttribute("book")Book book,@PathVariable("id")int id){
        bookDao.update(id,book);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id,Model model){
        model.addAttribute("book",bookDao.getBook(id));
        model.addAttribute("person",bookDao.getPersoner(id));
        return "book/show";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id")int id){
        bookDao.delete(id);
        return "redirect:/books";
    }
}
