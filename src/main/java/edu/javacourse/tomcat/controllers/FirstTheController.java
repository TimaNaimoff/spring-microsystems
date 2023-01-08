package edu.javacourse.tomcat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller("firstTeController")
@RequestMapping("/first")
public class FirstTheController {
    @GetMapping("/hello")   //Catching a param-request with annotations
    public String helloPage(@RequestParam(value="name",required = false)String name,
                            @RequestParam(value="surname",required=false)String surName){
        System.out.println("Hello, "+name+":"+surName);
        return "first/hello";
    }
    @GetMapping("/goodbye")  //Cathcing a param-request with HttpServletRequest
    public String goodByePage(HttpServletRequest params){
        System.out.println(params.getParameter("name")+":"+params.getParameter("surname"));
        return "first/goodbye";
    }
}
