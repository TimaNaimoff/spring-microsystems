package edu.javacourse.tomcat.controllers_act;


import edu.javacourse.tomcat.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
