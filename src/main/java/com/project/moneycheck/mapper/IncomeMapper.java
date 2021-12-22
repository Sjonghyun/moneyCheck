package com.project.moneycheck.mapper;

import com.project.moneycheck.dto.Income;
import com.project.moneycheck.mapper.sql.IncomeSQL;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface IncomeMapper {

    @SelectProvider(type = IncomeSQL.class, method = "select_income")
    List<Income> IncomeList(int u_no);

    @InsertProvider(type = IncomeSQL.class, method = "insert_income")
    void insert_income(@Param("income") Income income);

    @SelectProvider(type = IncomeSQL.class, method = "delete_income")
    void delete_income(int in_no);
}
