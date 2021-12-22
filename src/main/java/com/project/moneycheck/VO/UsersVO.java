package com.project.moneycheck.vo;

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
    private String u_nick;
    private String u_gender;
    private String u_jumin;
    private String u_phone;
    private String u_join;

    public void setU_nick(String u_nick) {
        this.u_nick = u_nick;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public UsersVO(String u_mail) { this.u_mail=u_mail; }
    public UsersVO(String u_nick, String u_mail) {
        super();
        this.u_nick=u_nick;
        this.u_mail=u_mail;
    }
}
