package com.project.moneycheck.mapper;

import com.project.moneycheck.dto.DateData;
import com.project.moneycheck.dto.Income;
import com.project.moneycheck.dto.ScheduleDto;
import com.project.moneycheck.dto.Spending;

import java.util.ArrayList;

public interface ScheduleDao {
    public int schedule_add(ScheduleDto scheduleDto);
    public int before_schedule_add_search(ScheduleDto scheduleDto);

    public ArrayList<ScheduleDto> schedule_list(DateData dateData);

    /* 수정, 삭제를 위한 리스트 불러오기 */
    /* 조회하기 */
    public ScheduleDto get(int idx);

    /* 수정하기 */
    public int update(ScheduleDto scheduleDto);

    /* 삭제하기 */
    public int delete(ScheduleDto scheduleDte);


}