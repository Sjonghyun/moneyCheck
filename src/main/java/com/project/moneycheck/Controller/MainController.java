package com.project.moneycheck.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class MainController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/login")
    public String login(){

        return "login";
    }
}
