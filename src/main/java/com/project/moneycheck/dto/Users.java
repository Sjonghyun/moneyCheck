package com.project.moneycheck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {
    private String u_no;
    private String u_mail;
    private String u_name;
    private String book_no;


}

