package com.project.moneycheck.mapper;

import com.project.moneycheck.dto.*;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface IncomeMapper {

    List<Income> IncomeList(int book_no,String date);

    void insert_income(@Param("income") Income income);

    void delete_income(int in_no);

    void excelUpload(Map<String,Object> paramMap)throws Exception;


    ArrayList<Income> income_list(DateData dateData, @Param("book_no")int book_no);

    int inCount(@Param("in_date") String in_date);

    List<IncomeVO> yearIn(@Param("book_no") int book_no);
    List<IncomeVO> monthIn(@Param("book_no") int book_no, @Param("date") String date);
    List<IncomeVO> dayIn(@Param("book_no") int book_no, @Param("date") String date);

    String inMoney(@Param("book_no") int book_no, @Param("date") String date);

}
