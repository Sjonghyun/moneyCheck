package com.project.moneycheck.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Message {

    private String messageId;
    private String name;
    private String message;
    private Date time;
    private String roomNumber;
    private String u_no;

}
