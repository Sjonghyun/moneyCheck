package com.project.moneycheck.service;

import com.project.moneycheck.dto.*;
import com.project.moneycheck.mapper.IncomeMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class IncomeService {
    private final IncomeMapper incomeMapper;

    public void insert_income(Income income){
        incomeMapper.insert_income(income);
    }

    public List<Income> incomeList(int book_no,String date){

        return incomeMapper.IncomeList(book_no,date);
    }

    public void delete_income(int in_no){
        incomeMapper.delete_income(in_no);
    }

    public ArrayList<Income> income_list(DateData dateData, @Param("book_no")int book_no){

        return incomeMapper.income_list(dateData,book_no);
    }

    public int inCount(@Param("in_date") String in_date){
        return incomeMapper.inCount(in_date);
    }

    public List<IncomeVO> yearIn(@Param("book_no") int book_no){
        return incomeMapper.yearIn(book_no);
    }
    public List<IncomeVO> monthIn(@Param("book_no") int book_no, @Param("date") String date){
        return incomeMapper.monthIn(book_no, date);
    }
    public List<IncomeVO> DayIn(@Param("book_no") int book_no, @Param("date") String date){
        return incomeMapper.dayIn(book_no, date);
    }

    public String inMoney(@Param("book_no") int book_no , @Param("date") String date){
        return incomeMapper.inMoney(book_no, date);
    }
}

