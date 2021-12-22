package com.project.moneycheck.mapper.sql;

import com.project.moneycheck.security.AuthValue;
import org.apache.ibatis.jdbc.SQL;

public class SecuritySQL {

    private static final String TABLE = "SECURITY";

//        �옉�꽦 諛⑸쾿�� �븘�옒 �럹�씠吏� 李몄“
//    https://mybatis.org/mybatis-3/ko/statement-builders.html
    public String loadUserByName(String username) {
//    	System.out.println(username);
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("USERNAME = #{username}")
                .toString();
    }

    public String insertAuthValue(AuthValue authValue) {
        return new SQL()
                .INSERT_INTO(TABLE)
                .VALUES("USERNAME", "#{authValue.username}")
                .VALUES("PASSWORD", "#{authValue.password}")
                .VALUES("ROLE", "#{authValue.role}")
                .toString();
    }

    public String updatePW(int sc_no, String encPwd) {
        return new SQL()
                .UPDATE(TABLE)
                .SET("PASSWORD = #{encPwd}")
                .WHERE("SC_NO = #{sc_no}")
                .toString();
    }

    public String updateUserPWD(int sc_no, String pwd, String new_pwd){
        return new SQL()
                .UPDATE(TABLE)
                .SET("PASSWORD = #{new_pwd}")
                .WHERE("SC_NO = #{sc_no}")
                .AND()
                .WHERE("PASSWORD = #{pwd}")
                .toString();
    }
}
