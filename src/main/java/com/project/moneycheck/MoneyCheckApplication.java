package com.project.moneycheck;

import com.project.moneycheck.Security.PrincipalUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class MoneyCheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyCheckApplication.class, args);
    }
}
