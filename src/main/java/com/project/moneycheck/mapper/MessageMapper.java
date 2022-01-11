package com.project.moneycheck.mapper;

import com.project.moneycheck.dto.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {

    void insertMsg(@Param("map")Map<String, Object> map);

    List<Message> loadMsg(int roomNumber);

}
