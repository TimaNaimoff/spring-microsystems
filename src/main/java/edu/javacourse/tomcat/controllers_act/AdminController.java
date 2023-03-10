package edu.javacourse.tomcat.controllers_act;

import edu.javacourse.tomcat.business.Person;
import edu.javacourse.tomcat.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private PeopleService peopleService;
    @Autowired
    public AdminController(PeopleService peopleService){
        this.peopleService=peopleService;
    }
    @GetMapping()
    public String getAdmin(Model model, @ModelAttribute("person") Person person) throws SQLException {
        model.addAttribute("people",peopleService.findAll());
        return "admin/adminus";
    }
    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("person")Person person){
        System.out.println(person.getId());
        return "redirect:/people";
    }
}
