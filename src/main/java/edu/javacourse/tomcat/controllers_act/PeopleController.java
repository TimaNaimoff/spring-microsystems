package edu.javacourse.tomcat.controllers_act;


import edu.javacourse.tomcat.business.Person;
//import edu.javacourse.tomcat.dao.PersonDAO;
import javax.validation.Valid;

//import edu.javacourse.tomcat.repo.PeopleRepository;
import edu.javacourse.tomcat.services.BookService;
import edu.javacourse.tomcat.services.PeopleService;
import edu.javacourse.tomcat.utils.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final BookService bookService;
    private PersonValidator personValidator;
    @Autowired
    public PeopleController(PeopleService peopleService,PersonValidator personValidator,BookService bookService){
        this.peopleService=peopleService;
        this.personValidator=personValidator;
        this.bookService=bookService;
    }
    @GetMapping()
    public String index(Model model) throws SQLException {
        bookService.findByAuthorName("The Endless Book");
        bookService.findByPerson(peopleService.findAll().get(0));
        peopleService.test();
        model.addAttribute("listOfPeoples",peopleService.findAll());
        return "/people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id,Model model){
        model.addAttribute("person",peopleService.findOnePerson(id));
//        model.addAttribute("books",dao.getBooksByPersonID(id));
        return "/people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person",new Person());
        return "/people/new";
    }
    @PostMapping
    public String create(@ModelAttribute("person")@Valid Person person, BindingResult resulter){
        personValidator.validate(person,resulter);
        if(resulter.hasErrors()) return "/people/new";
        peopleService.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id")int id, Model model){
          model.addAttribute("person",peopleService.findOnePerson(id));
          return "/people/edit";
    }
      @PatchMapping("/{id}")
      public String update(@ModelAttribute("person")@Valid Person person,BindingResult result,@PathVariable
              ("id")int id){
          personValidator.validate(person,result);

          if(result.hasErrors())return "/people/edit";
               peopleService.update(id,person);
               return "redirect:/people";
      }
      @DeleteMapping("/{id}")
      public String delete(@PathVariable("id")int id){
        peopleService.delete(id);
        return "redirect:/people";
      }
}
