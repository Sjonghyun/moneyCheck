package com.project.moneycheck.vo;

import com.project.moneycheck.security.AuthValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfo {
    private AuthValue authValue;
}
