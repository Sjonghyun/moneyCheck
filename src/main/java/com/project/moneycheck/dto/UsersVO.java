package com.project.moneycheck.dto;

import com.project.moneycheck.dto.AuthInfo;
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


    public UsersVO(String u_name, String u_mail) {
        super();
        this.u_name=u_name;
        this.u_mail=u_mail;
    }
}
