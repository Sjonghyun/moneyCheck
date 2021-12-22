package com.project.moneycheck.mapper;

import com.project.moneycheck.dto.Spending;
import com.project.moneycheck.dto.Users;
import com.project.moneycheck.mapper.sql.SpendingSQL;
import org.apache.ibatis.annotations.*;

import java.io.File;
import java.util.List;
import java.util.Map;

@Mapper
public interface SpendingMapper {

    @SelectProvider(type = SpendingSQL.class , method = "select_spending")
    List<Spending> spendingList(int u_no);

    @InsertProvider(type = SpendingSQL.class, method = "insert_spending")
    void insert_spending(@Param("spending")Spending spending);

    @SelectProvider(type = SpendingSQL.class, method = "delete_spending")
    void delete_spending(int sp_no);

    @SelectProvider(type = SpendingSQL.class , method = "spend_date")
    void spend_date(String date);

    @SelectProvider(type = SpendingSQL.class, method = "spendCount")
    String spendCount(int u_no , String date);

    @InsertProvider(type = SpendingSQL.class, method = "excelUpload")
    List<Map<String,String>> excelUpload(File destFile);
}
