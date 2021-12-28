package com.project.moneycheck.service;

import com.project.moneycheck.dto.Spending;
import com.project.moneycheck.mapper.SpendingMapper;
import com.project.moneycheck.util.ExcelRead;
import com.project.moneycheck.util.ExcelReadOption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
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

    public List<Spending> spendingList(int book_no){

        return spendingMapper.spendingList(book_no);
    }

    public void delete_spending(int sp_no){
        spendingMapper.delete_spending(sp_no);
    }

    public int spendCount(int book_no, String date){

        return spendingMapper.spendCount(book_no, date);
    }
}
