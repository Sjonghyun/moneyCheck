package com.project.moneycheck.dto;

import lombok.*;

//@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Users {
    private String u_no;
    private String u_mail;
    private String u_name;
    private String book_no;
    private String u_password;


}

