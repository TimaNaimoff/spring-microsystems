package edu.javacourse.tomcat.controllers_act;


import edu.javacourse.tomcat.business.Person;
import edu.javacourse.tomcat.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PersonDAO dao;
    @GetMapping()
    public String index(Model model){
        model.addAttribute("listOfPeoples",dao.getList());
        return "/people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id,Model model){
        model.addAttribute("person",dao.getOnePerson(id));
        return "/people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person",new Person());
        return "people/new";
    }
    @PostMapping
    public String create(@ModelAttribute("person")Person person){
        dao.save(person);
        return "redirect:/people";
    }
}
