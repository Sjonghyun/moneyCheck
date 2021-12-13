package com.project.moneycheck.Security.provider;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
//@AllArgsConstructor
public class NaverUserInfo implements OAuth2Info {

    // fianl 유무 차이 확인하기
    private Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderID() {
        return (String) this.attributes.get("id");
    }


    @Override
    public String getEmail() {
        return (String) this.attributes.get("email");
    }
}
