package com.project.moneycheck.security;

import com.project.moneycheck.mapper.AuthMapper;
import com.project.moneycheck.mapper.UsersMapper;
import com.project.moneycheck.vo.AuthInfo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PrincipalUserService implements UserDetailsService {

    private final AuthMapper authMapper;
    private final UsersMapper usersMapper;

//    security 로 일반 로그인 시 자동으로 호출 되는 메소드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthValue authValue = authMapper.loadUserByName(username);
        AuthInfo authInfo = null;

        switch (authValue.getRole()) {
            case "USER":
                authInfo = usersMapper.loadUserBySecurityNo(authValue.getSc_no());
                break;
//            case "HOST":
//                authInfo = hostMapper.loadUserBySecurityNo(authValue.getSc_no());
//                break;
//            case "ADMIN":
//                authInfo = adminMapper.loadUserBySecurityNo(authValue.getSc_no());
//                break;
        }

        if(authInfo != null) {
            return new PrincipalUser(authValue, authInfo);
        }
        return null;
    }
}
