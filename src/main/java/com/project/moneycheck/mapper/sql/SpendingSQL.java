package com.project.moneycheck.mapper.sql;

import com.project.moneycheck.dto.Spending;
import org.apache.ibatis.jdbc.SQL;

import java.io.File;
import java.util.List;

public class SpendingSQL {

    private static final String TABLE = "SPENDING";

    public String insert_spending(Spending spending){
//        return new SQL()
//                .INSERT_INTO(TABLE)
//                .VALUES("SP_DATE", "#{spending.sp_date}")
//                .VALUES("SP_USED","#{spending.sp_used}")
//                .VALUES("SP_MONEY","#{spending.sp_money}")
//                .VALUES("SP_ACCOUNT","#{spending.sp_account}")
//                .VALUES("SP_CATEGORY","#{spending.sp_category}")
//                .VALUES("SP_MEMO","#{spending.sp_memo}")
//                .VALUES("U_NO","#{spending.u_no}")
//                .toString();
        return new SQL()
                .INSERT_INTO(TABLE)
                .INTO_COLUMNS("SP_DATE","SP_USED","SP_MONEY","SP_ACCOUNT","SP_CATEGORY","SP_MEMO","U_NO")
                .INTO_VALUES("#{spending.sp_date}","#{spending.sp_used}","#{spending.sp_money}"
                        ,"#{spending.sp_account}","#{spending.sp_category}","#{spending.sp_memo}","#{spending.u_no}")
                .toString();
    }
    public String select_spending(int u_no){
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("U_NO = #{u_no}")
                .ORDER_BY("SP_DATE")
                .toString();
    }
    public String spend_date (String date){
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("SP_DATE = #{date}")
                .toString();
    }

    public String delete_spending(int sp_no){
        return new SQL()
                .DELETE_FROM(TABLE)
                .WHERE("SP_NO =#{sp_no}")
                .toString();
    }
//    SELECT COUNT(*) FROM spending WHERE U_NO = 1 AND date_format(sp_date , '%Y%m') = '202112';
    public String spendCount(int u_no, String date){
        return new SQL()
                .SELECT("COUNT(*)")
                .FROM(TABLE)
                .WHERE("U_NO = #{u_no}")
                .AND()
                .WHERE("DATE_FORMAT(SP_DATE, '%Y%m') = #{date}")
                .toString();
    }

    public String excelUpload(List excelContent){
        return new SQL()
                .INSERT_INTO(TABLE)
                .INTO_COLUMNS("SP_DATE","SP_USED","SP_MONEY")
                .INTO_VALUES()
                .toString();
    }

}
