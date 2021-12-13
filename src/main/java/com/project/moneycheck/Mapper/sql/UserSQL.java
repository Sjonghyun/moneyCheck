package com.project.moneycheck.Mapper.sql;

import com.project.moneycheck.DTO.Users;
import com.project.moneycheck.Security.AuthValue;
import com.project.moneycheck.VO.UsersVO;
import org.apache.ibatis.jdbc.SQL;

public class UserSQL {

    private static final String TABLE = "USERS";
    private static final String SNS_TABLE = "SNS_USERS";

    public static final String GET_USERS_LIST = "select * from "+TABLE;

    public String loadUserBySecurityNo(int sc_no) {
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("SC_NO = #{sc_no}")
                .toString();
    }
    public String loadForFindWD(AuthValue authValue, String name) {
        return new SQL()
                .SELECT("count(*)")
                .FROM(TABLE)
                .WHERE("SC_NO = #{authValue.sc_no}")
                .AND()
                .WHERE("U_NAME = #{name}")

                .toString();
    }


    public String loadUserBySNS(String snsID) {
//        System.out.println("loadUserBySNS: "+snsID);
        return new SQL()
                .SELECT(TABLE+".*")
                .FROM(TABLE)
                .JOIN(SNS_TABLE+" SNS on "+TABLE+".U_NO = SNS.U_NO" )
                .WHERE("SNS.ID = #{snsID}")
                .toString();
    }


    public String userInfo(int u_no) {
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("U_NO = #{u_no}")
                .toString();
    }
    public String insertBySNS(String snsID, UsersVO user) {
        return new SQL() {{
            INSERT_INTO(SNS_TABLE);
            VALUES("ID", "#{snsID}");
            VALUES("U_NO", "#{user.u_no}");
        }}.toString();
    }
    public String insertUser(AuthValue authValue, Users user) {
        return new SQL()
                .INSERT_INTO(TABLE)
                .VALUES("SC_NO", "#{authValue.sc_no}")
                .VALUES("U_MAIL", "#{authValue.username}")
                .VALUES("U_NAME", "#{user.u_name}")
                .toString();
    }


    public String insertEmptyUser(UsersVO user) {
        return new SQL()
                .INSERT_INTO(TABLE)
                .VALUES("U_MAIL", "#{user.u_mail}")
                .VALUES("U_NAME", "#{user.u_name}")
                .toString();
    }

    public String deleteUser(int u_no) {
        return new SQL()
                .DELETE_FROM(TABLE)
                .WHERE("U_NO = #{u_no}")
                .toString();
    }

    public String userList() {
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .toString();
    }

}