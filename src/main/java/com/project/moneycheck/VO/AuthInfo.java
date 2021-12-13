package com.project.moneycheck.VO;

import com.project.moneycheck.Security.AuthValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfo {
    private AuthValue authValue;
}
