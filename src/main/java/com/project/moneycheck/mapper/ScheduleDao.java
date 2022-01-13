package com.project.moneycheck.mapper;

import com.project.moneycheck.dto.DateData;
import com.project.moneycheck.dto.ScheduleDto;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface ScheduleDao {
     int schedule_add(ScheduleDto scheduleDto);
     int before_schedule_add_search(ScheduleDto scheduleDto);

     ArrayList<ScheduleDto> schedule_list(DateData dateData,@Param("book_no")int book_no);

     ScheduleDto get(@Param("idx")int idx, @Param("book_no") int book_no);

     int update(ScheduleDto scheduleDto);

     int delete(ScheduleDto scheduleDte);


}