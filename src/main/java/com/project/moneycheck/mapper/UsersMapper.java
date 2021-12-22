package com.project.moneycheck.mapper;

import com.project.moneycheck.dto.Users;
import com.project.moneycheck.mapper.sql.SecuritySQL;
import com.project.moneycheck.mapper.sql.UserSQL;
import com.project.moneycheck.security.AuthValue;
import com.project.moneycheck.vo.UsersVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsersMapper {

    @Select(UserSQL.GET_USERS_LIST)
    List<UsersVO> getUsersList();

//    @SelectProvider(type= UserSQL.class, method = "loadUserByName")
//    Users loadUserByName(@Param("username") String username);
    @SelectProvider(type = UserSQL.class, method = "loadUserBySecurityNo")
    UsersVO loadUserBySecurityNo(@Param("sc_no") int sc_no);

    @SelectProvider(type = UserSQL.class, method = "loadForFindWD")
    int loadForFindWD(@Param("authValue") AuthValue authValue, @Param("name") String name, @Param("jumin") String jumin);

    @SelectProvider(type = UserSQL.class, method = "loadUserBySNS")
    UsersVO loadUserBySNS(@Param("snsID") String snsID);

    @SelectProvider(type = UserSQL.class, method = "userInfo")
    UsersVO userInfo(int u_no);

    @Options(useGeneratedKeys = true, keyProperty = "user.u_no")
    @InsertProvider(type = UserSQL.class, method = "insertUser")
    void insertUser(@Param("authValue") AuthValue authValue, @Param("user") Users user);
//    int insert(HashMap<String, Object> user_map);

    @Options(useGeneratedKeys = true, keyProperty = "user.u_no")
    @InsertProvider(type = UserSQL.class, method = "insertEmptyUser")
    void insertEmptyUser( @Param("user") UsersVO user);

    @InsertProvider(type = UserSQL.class, method = "insertBySNS")
    void insertBySNS(@Param("snsID") String snsID, @Param("user") UsersVO user);

    @UpdateProvider(type = UserSQL.class, method = "updateUserNickPhone")
//    int update(HashMap<String, Object> user_map);
    int updateUserNickPhone(@Param("user") Users user);

    @DeleteProvider(type = UserSQL.class, method = "deleteUser")
    int delete(int u_no);

    @SelectProvider(type = UserSQL.class, method = "nickCheck")
    int nickCheck(String u_nick);
    // �땳�꽕�엫 以묐났泥댄겕
    @SelectProvider(type = UserSQL.class, method = "check_mail")
    int check_mail(String u_mail);
    //�씠硫붿씪 以묐났泥댄겕
/*	@InsertProvider(value = UserSQL.class, method = "pw_check")
    String pw_check(String no);*/

	@UpdateProvider(type = SecuritySQL.class, method = "updateUserPWD")
    int updateUserPWD(@Param("sc_no") int sc_no, @Param("pwd") String pwd, @Param("new_pwd") String new_pwd);
	
    //이메일 중복체크
    @SelectProvider(type = UserSQL.class, method = "findID")
    String findID(String name, String jumin);
    
    @SelectProvider(type = UserSQL.class, method = "userList")
    List<Users> userList();


    @InsertProvider(type = UserSQL.class, method = "reviewWrite")
    void reviewWrite(String rv_content, int rv_star, int rv_uno , int rv_ghno);

}

