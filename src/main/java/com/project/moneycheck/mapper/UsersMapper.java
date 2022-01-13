package com.project.moneycheck.mapper;

import com.project.moneycheck.dto.Users;
import com.project.moneycheck.dto.UsersVO;
import com.project.moneycheck.security.AuthValue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsersMapper {

    UsersVO loadUserBySNS(@Param("snsID") String snsID);

    void insertEmptyUser( @Param("user") UsersVO user);

    void insertBySNS(@Param("snsID") String snsID, @Param("user") UsersVO user);

    int userMail (@Param("u_mail") String u_mail);

    void addBookno( @Param("user") UsersVO user);

    void connectUsers(@Param("book_no")String book_no, @Param("u_mail") String u_mail);

    List<Users> loadUsers(@Param("book_no")int book_no);

    void deleteUser(@Param("u_no")int u_no);
    void deleteSns(@Param("u_no")int u_no);


}

