package com.project.moneycheck.service;


import com.project.moneycheck.dto.Users;
import com.project.moneycheck.mapper.UsersMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Users> loadUsers(int book_no){

        return usersMapper.loadUsers(book_no);
    }

    public Users idPassword(String u_mail, String u_password){
        return usersMapper.idPassword(u_mail, u_password);
    }
    public void deleteUser(int u_no){
        usersMapper.deleteUser(u_no);
    }
    public void deleteSns(int u_no){
        usersMapper.deleteSns(u_no);

    }

}
