package com.project.moneycheck.mapper.sql;

import com.project.moneycheck.dto.Income;
import com.project.moneycheck.dto.Spending;
import org.apache.ibatis.jdbc.SQL;

public class IncomeSQL {

    private static final String TABLE = "INCOME";

    public String insert_income(Income income){
        return new SQL()
                .INSERT_INTO(TABLE)
                .INTO_COLUMNS("IN_DATE","IN_USED","IN_MONEY","IN_ACCOUNT","IN_CATEGORY","IN_MEMO","U_NO")
                .INTO_VALUES("#{income.in_date}","#{income.in_used}","#{income.in_money}"
                        ,"#{income.in_account}","#{income.in_category}","#{income.in_memo}","#{income.u_no}")
                .toString();
    }
    public String select_income(int u_no){
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("U_NO = #{u_no}")
                .ORDER_BY("IN_DATE")
                .toString();
    }
    public String income_date (String date){
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("IN_DATE = #{date}")
                .toString();
    }

    public String delete_income(int in_no){
        return new SQL()
                .DELETE_FROM(TABLE)
                .WHERE("IN_NO =#{in_no}")
                .toString();
    }
    public String incomeCount(int u_no, String date){
        return new SQL()
                .SELECT("COUNT(*)")
                .FROM(TABLE)
                .WHERE("U_NO = #{u_no}")
                .AND()
                .WHERE("DATE_FORMAT(SP_DATE, '%Y%m') = #{date}")
                .toString();
    }
}
