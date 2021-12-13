package com.project.moneycheck.Security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthValue {
    private String username;
    private String password;
    private int sc_no;
    private String role;
}