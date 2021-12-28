package com.project.moneycheck.mapper;

import com.project.moneycheck.dto.DateData;
import com.project.moneycheck.dto.Spending;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface SpendingMapper {

    List<Spending> spendingList(int book_no);

    void insert_spending(@Param("spending") Spending spending);

    void delete_spending(int sp_no);

    void spend_date(String date);

    int spendCount(int book_no , String date);

    void excelUpload(Map<String,Object> paramMap)throws Exception;

    ArrayList<Spending> spend_list(DateData dateData);

}
