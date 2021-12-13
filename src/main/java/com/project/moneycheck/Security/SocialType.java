package com.project.moneycheck.Security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SocialType {
    NAVER("NAVER"),
    KAKAO("KAKAO"),
    FACEBOOK("FACEBOOK"),
    GITHUB("GITHUB"),
    GOOGLE("GOOGLE");

    private final String snsName;

    public String getSocialType() { return this.snsName; }
    public boolean isEquals(String authoity) { return this.snsName.equals(authoity); }

}
