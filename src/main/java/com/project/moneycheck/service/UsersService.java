package com.project.moneycheck.service;


import com.project.moneycheck.mapper.UsersMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsersService {
    private final UsersMapper usersMapper;

    public int userMail(String u_mail){
        return usersMapper.userMail(u_mail);
    }
    public void connectUsers(String book_no,String u_mail){

        usersMapper.connectUsers(book_no,u_mail);
    }


}
