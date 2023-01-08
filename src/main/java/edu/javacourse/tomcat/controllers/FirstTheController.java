package edu.javacourse.tomcat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller("firstTeController")
@RequestMapping("/first")
public class FirstTheController {
    @GetMapping("/hello")   //Catching a param-request with annotations
    public String helloPage(@RequestParam(value="name",required = false)String name,
                            @RequestParam(value="surname",required=false)String surName, Model
                            model){
        model.addAttribute("message",name+":"+surName);
        return "first/hello";
    }
    @GetMapping("/goodbye")  //Cathcing a param-request with HttpServletRequest
    public String goodByePage(HttpServletRequest params){
        System.out.println(params.getParameter("name")+":"+params.getParameter("surname"));
        return "first/goodbye";
    }
    @GetMapping("/calulus")
    public String calculeThem(@RequestParam(name="number1")String number1,
                              @RequestParam(name="work")String work,
                              @RequestParam(name="number2")String number2,
                              Model model){
    double  count=0;
    try {
        switch (work) {
            case "*":
                count = Integer.parseInt(number1) * Integer.parseInt(number2);
                break;
            case "/":
            case ":":
                count = Integer.parseInt(number1) / (1.0*Integer.parseInt(number2));
                break;
            case "-":
                count = Integer.parseInt(number1) - Integer.parseInt(number2);
                break;
            case "+":
                count = Integer.parseInt(number1) + Integer.parseInt(number2);
                break;
        }
    }catch(Exception e){
        model.addAttribute("value","Stupid trying,go a way!");
        return "first/calculatoris";

    }
    model.addAttribute("value",count);
        return "first/calculatoris";

    }
}
