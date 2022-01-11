package com.project.moneycheck.mapper;

import com.project.moneycheck.dto.DateData;
import com.project.moneycheck.dto.ScheduleDto;
import com.project.moneycheck.dto.Spending;
import com.project.moneycheck.dto.SpendingVO;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface SpendingMapper {

    List<Spending> spendingList(int book_no,String date);

    void insert_spending(@Param("spending") Spending spending);

    void delete_spending(int sp_no);

    void spend_date(String date);

    int spendCount(int book_no , String date);

    void excelUpload(Map<String,Object> paramMap)throws Exception;

    ArrayList<Spending> spend_list(DateData dateData, @Param("book_no")int book_no);

    int spCount(@Param("sp_date") String sp_date);

    List<SpendingVO> yearSp(@Param("book_no") int book_no);
    List<SpendingVO> monthSp(@Param("book_no") int book_no, @Param("date") String date);
    List<SpendingVO> daySp(@Param("book_no") int book_no, @Param("date") String date);

    String spMoney(@Param("book_no") int book_no, @Param("date") String date);

}
