package com.project.moneycheck.service;

import com.project.moneycheck.dto.Income;
import com.project.moneycheck.mapper.IncomeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class IncomeService {
    private final IncomeMapper incomeMapper;

    public void insert_spending(Income income){
        incomeMapper.insert_income(income);
    }

    public List<Income> incomeList(int book_no){

        return incomeMapper.IncomeList(book_no);
    }

    public void delete_income(int in_no){
        incomeMapper.delete_income(in_no);
    }
}

