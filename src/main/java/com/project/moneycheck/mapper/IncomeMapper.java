package com.project.moneycheck.mapper;

import com.project.moneycheck.dto.Income;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface IncomeMapper {

    List<Income> IncomeList(int u_no);

    void insert_income(@Param("income") Income income);

    void delete_income(int in_no);
}
