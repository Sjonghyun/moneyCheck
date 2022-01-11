package com.project.moneycheck.service;

import com.project.moneycheck.dto.Message;
import com.project.moneycheck.mapper.MessageMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageMapper messageMapper;

    public void insertMsg(@Param("map") Map<String,Object> map){
        messageMapper.insertMsg(map);
    }

    public List<Message> loadMsg(int roomNumber){
        return messageMapper.loadMsg(roomNumber);
    }
}
