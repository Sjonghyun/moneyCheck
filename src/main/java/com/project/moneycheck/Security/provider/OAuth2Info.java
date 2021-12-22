package com.project.moneycheck.security.provider;

public interface OAuth2Info {
    String getProvider();
    String getProviderID();
    String getNickName();
    String getEmail();
}
