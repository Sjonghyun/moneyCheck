package com.project.moneycheck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Income {
    private int in_no;
    private Date in_date;
    private String in_used;
    private int in_money;
    private String in_account;
    private String in_category;
    private String in_memo;
    private int book_no;
}