package com.project.moneycheck.Mapper;

import com.project.moneycheck.DTO.Users;
import com.project.moneycheck.Mapper.sql.UserSQL;
import com.project.moneycheck.Security.AuthValue;
import com.project.moneycheck.VO.UsersVO;
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
    int loadForFindWD(@Param("authValue") AuthValue authValue, @Param("name") String name);

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



    @DeleteProvider(type = UserSQL.class, method = "deleteUser")
    int delete(int u_no);




}
