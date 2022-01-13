package com.project.moneycheck.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UsersVO extends AuthInfo {
    private int u_no;
    private String u_mail;
    private String u_name;
    private int book_no;


    public UsersVO(String u_name, String u_mail) {
        super();
        this.u_name=u_name;
        this.u_mail=u_mail;
    }
}
