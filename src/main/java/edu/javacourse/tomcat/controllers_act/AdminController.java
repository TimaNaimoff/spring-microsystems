package edu.javacourse.tomcat.controllers_act;

import edu.javacourse.tomcat.business.Person;
import edu.javacourse.tomcat.dao.PersonDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDAO personDAO;
    public AdminController(PersonDAO personDAO){
        this.personDAO=personDAO;
    }
    @GetMapping()
    public String getAdmin(Model model, @ModelAttribute("person") Person person) throws SQLException {
        model.addAttribute("listOfPeoples",personDAO.index());
        return "adminus";
    }
    @PostMapping("/add")
    public String postAdmin(){

    }
}
