package com.project.moneycheck.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ScheduleDto {
    int schedule_idx;
    int schedule_num;
    String schedule_subject;
    String schedule_desc;
    String schedule_date;
    String schedule_share;
    String schedule_mycolor;

    public int getSchedule_idx() {
        return schedule_idx;
    }
    public void setSchedule_idx(int schedule_idx) {
        this.schedule_idx = schedule_idx;
    }

    public void setSchedule_mycolor(String schedule_mycolor) {
        this.schedule_mycolor =schedule_mycolor;
    }
    @Override
    public String toString() {
        return "ScheduleDto [schedule_idx=" + schedule_idx + ", schedule_num=" + schedule_num + ", schedule_subject="
                + schedule_subject + ", schedule_desc=" + schedule_desc + ", schedule_date=" + schedule_date + ", schedule_share="+schedule_share
                +", schedule_mycolor="+schedule_mycolor +"]";
    }
}
