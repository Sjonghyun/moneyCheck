package com.project.moneycheck.VO;

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
    private String u_password;


    public UsersVO(String u_mail) { this.u_mail=u_mail; }

}