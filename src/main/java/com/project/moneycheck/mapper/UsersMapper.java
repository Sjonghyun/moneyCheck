package com.project.moneycheck.mapper;

import com.project.moneycheck.dto.UsersVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UsersMapper {

    UsersVO loadUserBySNS(@Param("snsID") String snsID);

    void insertEmptyUser( @Param("user") UsersVO user);

    void insertBySNS(@Param("snsID") String snsID, @Param("user") UsersVO user);

    int userMail (@Param("u_mail") String u_mail);

    void addBookno( @Param("user") UsersVO user);

    void connectUsers(@Param("book_no")String book_no, @Param("u_mail") String u_mail);

    void disconnect();
}

