package com.project.moneycheck.service;

import com.project.moneycheck.dto.DateData;
import com.project.moneycheck.dto.Spending;
import com.project.moneycheck.dto.SpendingVO;
import com.project.moneycheck.mapper.SpendingMapper;
import com.project.moneycheck.util.ExcelRead;
import com.project.moneycheck.util.ExcelReadOption;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class SpendingService {
    private final SpendingMapper spendingMapper;

    public void insert_spending(Spending spending){
        spendingMapper.insert_spending(spending);
    }

    public List<Spending> spendingList(int book_no,String date){

        return spendingMapper.spendingList(book_no,date);
    }

    public void delete_spending(int sp_no){
        spendingMapper.delete_spending(sp_no);
    }

    public ArrayList<Spending> spend_list(DateData dateData, @Param("book_no")int book_no){

        return spendingMapper.spend_list(dateData,book_no);
    }


    public List<SpendingVO> yearSp(@Param("book_no") int book_no){
        return spendingMapper.yearSp(book_no);
    }
    public List<SpendingVO> monthSp(@Param("book_no") int book_no, @Param("date") String date){
        return spendingMapper.monthSp(book_no, date);
    }
    public List<SpendingVO> DaySp(@Param("book_no") int book_no, @Param("date") String date){
        return spendingMapper.daySp(book_no, date);
    }

    public String spMoney(@Param("book_no") int book_no , @Param("date") String date){
        return spendingMapper.spMoney(book_no, date);
    }
}
