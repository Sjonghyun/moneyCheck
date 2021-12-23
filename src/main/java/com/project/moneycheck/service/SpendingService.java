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

    public List<Spending> spendingList(int u_no){

        return spendingMapper.spendingList(u_no);
    }

    public void delete_spending(int sp_no){
        spendingMapper.delete_spending(sp_no);
    }

    public String spendCount(int u_no, String date){

        return spendingMapper.spendCount(u_no, date);
    }
}
