package com.project.moneycheck.Controller;

import com.project.moneycheck.service.IncomeService;
import com.project.moneycheck.service.SpendingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class MainController {

    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/sessionCheck")
    public String userInfo(){

        return "userInfo";
    }
//    @GetMapping("/insert_income")
//    public String insert_income(){
//
//
//        return "insert_income";
//    }
//    @GetMapping("/insert_spend")
//    public String insert_spend(){
//
//
//        return "insert_spend";
//    }
//

}
