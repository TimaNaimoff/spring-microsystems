package edu.javacourse.tomcat.controllers_act;


import edu.javacourse.tomcat.business.Person;
import edu.javacourse.tomcat.dao.PersonDAO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        return "/people/new";
    }
    @PostMapping
    public String create(@ModelAttribute("person")@Valid Person person, BindingResult resulter){
        if(resulter.hasErrors()) return "/people/new";
        dao.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id")int id, Model model){
          model.addAttribute("person",dao.getOnePerson(id));
          return "/people/edit";
    }
      @PatchMapping("/{id}")
      public String update(@ModelAttribute("person")@Valid Person person,BindingResult result,@PathVariable
              ("id")int id){
        if(result.hasErrors())return "/people/edit";
              dao.update(id,person);
              return "redirect:/people";
      }
      @DeleteMapping("/{id}")
      public String delete(@PathVariable("id")int id){
        dao.remove(id);
        return "redirect:/people";
      }
}
