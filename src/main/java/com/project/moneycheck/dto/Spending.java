package com.project.moneycheck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Spending {
    private int sp_no;
    private Date sp_date;
    private String sp_used;
    private int sp_money;
    private String sp_account;
    private String sp_category;
    private String sp_memo;
    private int u_no;
}
