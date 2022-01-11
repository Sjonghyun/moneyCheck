package com.project.moneycheck.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString
@Getter
@Setter
public class ScheduleDto {

    int schedule_idx;
    int schedule_num;
    String schedule_subject;
    String schedule_desc;
    Date schedule_date;
    int book_no;


    @Override
    public String toString() {
        return "ScheduleDto [schedule_idx=" + schedule_idx + ", schedule_num=" + schedule_num + ", schedule_subject="
                + schedule_subject + ", schedule_desc=" + schedule_desc + ", schedule_date=" + schedule_date+", book_no="+book_no+"]";
    }
}