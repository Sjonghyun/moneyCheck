package com.project.moneycheck.Security.provider;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class KakaoUserInfo implements OAuth2Info {

    private Map<String, Object> attributes;
    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderID() {
        return String.valueOf(this.attributes.get("id"));
    }


    @Override
    public String getEmail() {
        HashMap<String, Object> kakao_account = (HashMap<String, Object>) this.attributes.get("kakao_account");
        return (String) kakao_account.get("email");
    }
}
