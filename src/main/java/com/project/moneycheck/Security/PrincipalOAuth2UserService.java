package com.project.moneycheck.Security;

import com.project.moneycheck.Mapper.UsersMapper;
import com.project.moneycheck.Security.provider.GoogleUserInfo;
import com.project.moneycheck.Security.provider.KakaoUserInfo;
import com.project.moneycheck.Security.provider.NaverUserInfo;
import com.project.moneycheck.Security.provider.OAuth2Info;
import com.project.moneycheck.VO.UsersVO;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    private final UsersMapper usersMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String registrationID = userRequest.getClientRegistration().getRegistrationId();
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2Info oAuth2Info = null;

        switch(registrationID) {
            case "google":
                oAuth2Info = new GoogleUserInfo(oAuth2User.getAttributes());
                break;
            case "naver":
                oAuth2Info = new NaverUserInfo(oAuth2User.getAttributes());
                break;
            case "kakao":
                oAuth2Info = new KakaoUserInfo(oAuth2User.getAttributes());
                break;
        }

        String snsID = oAuth2Info.getProvider()+"_"+oAuth2Info.getProviderID();

        System.out.println("snsID: "+snsID);

        UsersVO userInfo = usersMapper.loadUserBySNS(snsID);

        if (userInfo == null) {
            userInfo = new UsersVO(oAuth2Info.getEmail());
            usersMapper.insertEmptyUser(userInfo);
            usersMapper.insertBySNS(snsID, userInfo);
        }

        AuthValue authValue = new AuthValue();
        authValue.setUsername(oAuth2Info.getEmail());
        authValue.setRole("USER");
        userInfo.setAuthValue(authValue);

        return new PrincipalUser(userInfo, oAuth2User.getAttributes());
    }
}