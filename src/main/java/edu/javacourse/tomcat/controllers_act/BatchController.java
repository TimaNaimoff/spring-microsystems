package edu.javacourse.tomcat.controllers_act;

import edu.javacourse.tomcat.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test-batch-update")
public class BatchController {
    @Autowired
    private final PersonDAO dao;

    public BatchController(PersonDAO dao){
        this.dao=dao;
    }
    @GetMapping()
    public String index(){
        return "batch/index";
    }
    @GetMapping("/with")
    public String withBatch(){
        dao.testMultipleUpdate();
        return "redirect:/people";
    }
    @GetMapping("/without")
    public String withoutBatch(){
        dao.testBatchUpdate();
        return "redirect:/people";
    }

}
