package com.project.moneycheck.security;

import com.project.moneycheck.dto.AuthInfo;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@NoArgsConstructor
@ToString
public class PrincipalUser implements UserDetails, OAuth2User {

    private AuthInfo authInfo;
//    private UsersVO user;
    private Map<String, Object> attributes;

////    일반 로그인을 위한 생성자
//    public PrincipalUser(AuthValue authValue, AuthInfo authInfo) {
//        this.authInfo = authInfo;
//        this.authInfo.setAuthValue(authValue);
//    }

//    OAuth2User 로그인을 위한 생성자
    public PrincipalUser(AuthInfo authInfo, Map<String, Object> attributes) {
        this.authInfo = authInfo;
        this.attributes = attributes;
    }

    public String getAuthRole() {
        return this.authInfo.getAuthValue().getRole();
    }

    public Object getAuthInfo(){
        return authInfo;
    }

    //   for OAuth2User
    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }
    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }


//  for  AuthenticationPrincipal
    @Override
    public String getName() {
//        구글만 쓸 거면 의미가 있는데 여러 sns 사용하는 경우 큰 쓸모가 없음.
        return getUsername();
    }

    @Override
    public String getPassword() {
//        return authInfo.getAuthValue().getPassword();
        return "";
    }

    @Override
    public String getUsername() {
        String name = authInfo.getAuthValue().getUsername();
        if(name == null) {
            name = "authInfo";
        }
        return name;
    }


//   for UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> "ROLE_"+authInfo.getAuthValue().getRole());
        return collection;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
