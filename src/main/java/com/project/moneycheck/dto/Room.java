package com.project.moneycheck.dto;

public class Room {
    int roomNumber;
    String roomName;

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String String() {
        return "Room[roomNumber="+roomNumber + ", roomName="+ roomName + "]";
    }
}

