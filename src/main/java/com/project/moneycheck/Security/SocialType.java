package com.project.moneycheck.security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SocialType {
    NAVER("NAVER"),
    KAKAO("KAKAO"),
    GOOGLE("GOOGLE");

    private final String snsName;

    public String getSocialType() { return this.snsName; }
    public boolean isEquals(String authoity) { return this.snsName.equals(authoity); }

}
