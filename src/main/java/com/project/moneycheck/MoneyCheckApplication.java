package com.project.moneycheck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan (value = {"com.project.moneycheck.mapper"})
@SpringBootApplication
public class MoneyCheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyCheckApplication.class, args);
    }
}
