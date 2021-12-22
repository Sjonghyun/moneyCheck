package com.project.moneycheck.mapper.sql;

import com.project.moneycheck.dto.Users;
import com.project.moneycheck.security.AuthValue;
import com.project.moneycheck.vo.UsersVO;
import org.apache.ibatis.jdbc.SQL;

public class UserSQL {

    private static final String TABLE = "USERS";
    private static final String SNS_TABLE = "SNS_USERS";

    public static final String GET_USERS_LIST = "select * from "+TABLE;

//    �옉�꽦 諛⑸쾿�� �븘�옒 �럹�씠吏� 李몄“
//    https://mybatis.org/mybatis-3/ko/statement-builders.html
//    public String loadUserByName(String username) {
//        return new SQL()
//                .SELECT("U_MAIL")
//                .FROM(TABLE)
//                .WHERE("U_MAIL = #{username}")
//                .toString();
//    }

    public String loadUserBySecurityNo(int sc_no) {
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("SC_NO = #{sc_no}")
                .toString();
    }

    public String loadForFindWD(AuthValue authValue, String name, String jumin) {
        return new SQL()
                .SELECT("count(*)")
                .FROM(TABLE)
                .WHERE("SC_NO = #{authValue.sc_no}")
                .AND()
                .WHERE("U_NAME = #{name}")
                .AND()
                .WHERE("U_JUMIN = #{jumin}")
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
//        �뀒�씠釉� 留뚮뱾�뼱�꽌 議곗씤 臾� �솗�씤�븯怨� �옉�꽦�븯湲�
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
                .VALUES("U_NICK", "#{user.u_nick}")
                .VALUES("U_GENDER", "#{user.u_gender}")
                .VALUES("U_JUMIN", "#{user.u_jumin}")
                .VALUES("U_PHONE", "#{user.u_phone}")
                .toString();
    }

    public String insertEmptyUser(UsersVO user) {
        return new SQL()
                .INSERT_INTO(TABLE)
                .VALUES("U_MAIL", "#{user.u_mail}")
                .VALUES("U_NAME", "#{user.u_name}")
                .VALUES("U_NICK", "#{user.u_nick}")
                .VALUES("U_GENDER", "#{user.u_gender}")
                .VALUES("U_JUMIN", "#{user.u_jumin}")
                .VALUES("U_PHONE", "#{user.u_phone}")
                .toString();
    }



    public String updateUserNickPhone(Users user) {
        return new SQL() {{
            UPDATE(TABLE);
            SET("U_NICK = #{user.u_nick}");
//            SET("U_PWD = #{user.u_pwd}");
//            SET("U_GENDER = #{user.u_gender}");
//            SET("U_JUMIN = #{user.u_jumin}");
            SET("U_PHONE = #{user.u_phone}");
            WHERE("U_NO = #{user.u_no}");
        }}.toString();
    }

    public String deleteUser(int u_no) {
        return new SQL()
                .DELETE_FROM(TABLE)
                .WHERE("U_NO = #{u_no}")
                .toString();
    }

    public String nickCheck(String u_nick){
        return new SQL()
                .SELECT("count(*)")
                .FROM(TABLE)
                .WHERE("U_NICK = #{U_NICK}")
                .toString();
    }

    public String check_mail(String u_mail){
        return new SQL()
                .SELECT("count(*)")
                .FROM("SECURITY")
                .WHERE("USERNAME=#{u_mail}")
                .toString();
    }

    public String findID(String name,String jumin){
        return new SQL()
                .SELECT ("U_MAIL")
                .FROM(TABLE)
                .WHERE("U_NAME=#{name} AND U_JUMIN=#{jumin}")
                .toString();
    }

    public String userList() {
    	return new SQL()
    			.SELECT("*")
    			.FROM(TABLE)
    			.toString();
    }

    // UserMypage getBook limit 1 current
    public String getBook(String u_no){
        return new SQL()
                .SELECT("*")
                .FROM("BOOKING")
                .JOIN("ROOMS R on R.R_NO = BOOKING.B_RNO")
                .JOIN("GHOUSE G on G.GH_NO = R.R_GHNO")
                .JOIN("HOST H on H.H_NO = G.GH_HNO")
                .WHERE("B_UNO = #{u_no}")
                .ORDER_BY("B_JOIN desc")
                .LIMIT(1)
                .toString();
    }

    // UsermyPage-> getBookList
    public String getMyBookList(String u_no){
        return new SQL()
                .SELECT("GH_NO, GH_IMAGE, GH_NAME, GH_ADDR1, GH_ADDR2, " +
                        "       R_NO, R_NAME, R_FEE, " +
                        "       U_NO, U_PHONE, U_NICK, U_GENDER, B_NO, DATE_FORMAT(B_IN, '%Y-%m-%d')'B_IN' " +
                        "        , DATE_FORMAT(B_OUT, '%Y-%m-%d')'B_OUT', B_PNO, B_STATE, B_JOIN, " +
                        "       H_NO, H_BANK, H_PHONE")
                .FROM(TABLE)
                .JOIN("BOOKING B on USERS.U_NO = B.B_UNO")
                .JOIN("ROOMS R on R.R_NO = B.B_RNO")
                .JOIN("GHOUSE G on G.GH_NO = R.R_GHNO")
                .JOIN("HOST H on H.H_NO = G.GH_HNO")
                .WHERE("U_NO = #{u_no}")
                .ORDER_BY("B_JOIN DESC")
                .toString();
    }

    public String myBookSelectOneDetail(String b_no, String u_no){
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .JOIN("BOOKING B on USERS.U_NO = B.B_UNO")
                .JOIN("ROOMS R on R.R_NO = B.B_RNO")
                .JOIN("GHOUSE G on G.GH_NO = R.R_GHNO")
                .JOIN("HOST H on H.H_NO = G.GH_HNO")
                .WHERE("B_NO=#{b_no}")
                .WHERE("U_NO=#{u_no}")
                .toString();
    }

    public String userBookingCancel(int b_no, int u_no){
        return new SQL()
                .UPDATE("BOOKING")
                .SET("B_STATE = 3")
                .WHERE("B_NO = #{b_no}")
                .WHERE("B_UNO = #{u_no}")
                .toString();
    }

    public String reviewWrite(String rv_content, int rv_star, int rv_uno , int rv_ghno){
        return new SQL()
                .INSERT_INTO("REVIEWS")
                .VALUES("rv_content","#{rv_content}")
                .VALUES("rv_star","#{rv_star}")
                .VALUES("rv_uno","#{rv_uno}")
                .VALUES("rv_ghno","#{rv_ghno}")
                .toString();
    }

}
