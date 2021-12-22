package com.project.moneycheck.service;


import com.project.moneycheck.dto.Users;
import com.project.moneycheck.mapper.AuthMapper;
import com.project.moneycheck.mapper.UsersMapper;
import com.project.moneycheck.security.AuthValue;
import com.project.moneycheck.security.PrincipalUser;
import com.project.moneycheck.vo.UsersVO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UsersService {

    private final UsersMapper usersMapper;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    public void insertUser(AuthValue authValue, Users user) {
        String mail = authValue.getUsername().replace(",", "");
        String encPwd = passwordEncoder.encode(authValue.getPassword());
        authValue.setUsername(mail);
        authValue.setPassword(encPwd);
        authValue.setRole("USER");
        authMapper.insertAuthValue(authValue);

        String jumin = user.getU_jumin().replace(",", "");
        String phone = user.getU_phone().replace(",", "");
        user.setU_jumin(jumin);
        user.setU_phone(phone);
        usersMapper.insertUser(authValue, user);
    }

    public int userDelete(UsersVO user) {
        // 유저가 삭제되면 무엇은 지우고 무엇은 유지 할 것 인지 정해야 함
        return  usersMapper.delete(user.getU_no());
    }

    public int nickCheck(String u_nick){
        // 닉네임 중복체크
        return usersMapper.nickCheck(u_nick);
    }

/*    public String pw_check(String no) {
    	return usersMapper.pw_check(no);
    }*/

    public String findID(String name, String jumin){
        return usersMapper.findID(name,jumin);
    }
    public List<Users> userList(){
		return usersMapper.userList();
	}

    public void updateUserInfo(PrincipalUser principalUser, Users user) {
        UsersVO orginUser = (UsersVO) principalUser.getAuthInfo();
        String phone = user.getU_phone().replace(",", "");
        user.setU_no(String.valueOf(orginUser.getU_no()));
        user.setU_phone(phone);
        int isSuccess = usersMapper.updateUserNickPhone(user);
//        System.out.println("isSuccess"+isSuccess);
        if (isSuccess != 0){
            orginUser.setU_nick(user.getU_nick());
            orginUser.setU_phone(phone);
        }
    }
    public UsersVO userInfo(int u_no){
    	return usersMapper.userInfo(u_no);
    }

    public void reviewWrite(String rv_content, int rv_star, int rv_uno , int rv_ghno){
        usersMapper.reviewWrite(rv_content,  rv_star,  rv_uno,  rv_ghno);
    }
}
