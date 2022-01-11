package com.project.moneycheck.Controller;

import com.project.moneycheck.dto.Message;
import com.project.moneycheck.dto.Room;
import com.project.moneycheck.dto.Users;
import com.project.moneycheck.service.MessageService;
import com.project.moneycheck.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ChatController {

    private final MessageService messageService;
    private final UsersService usersService;

    List<Room> roomList = new ArrayList<>();
    static int roomNumber = 0;

    @RequestMapping("/chat")
    public ModelAndView chat(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chat");
        return mv;
    }

    //방페이지
    @RequestMapping("/room")
    public ModelAndView room(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("room");
        return mv;
    }
    
    //방생성
    @RequestMapping("/createRoom")
    public @ResponseBody List<Room> createRoom(@RequestParam HashMap<Object,Object> params){
        String roomName = (String) params.get("roomName");
        if(roomName != null && !roomName.trim().equals("")){
            Room room = new Room();
            room.setRoomNumber(++roomNumber);
            room.setRoomName(roomName);
            roomList.add(room);
        }
        return roomList;
    }
    
    //방정보가져오기
    @RequestMapping("/getRoom")
    public @ResponseBody List<Room> getRoom(@RequestParam HashMap<Object, Object> params){
        return roomList;
    }

    
    //채팅방
    @RequestMapping("/moveChating")
    public ModelAndView chating(@RequestParam HashMap<Object,Object> params){
        List<Users> usersList = usersService.loadUsers(Integer.parseInt(params.get("book_no").toString()));
        List<Message> messageList = this.messageService.loadMsg(Integer.parseInt(params.get("roomNumber").toString()));

        ModelAndView mv = new ModelAndView();
        mv.addObject("usersList",usersList);
        mv.addObject("messageList",messageList);
        int roomNumber = Integer.parseInt((String) params.get("roomNumber"));

//        List<Room> new_list = roomList.stream().filter(o->o.getRoomNumber() == roomNumber).collect(Collectors.toList());
            mv.addObject("roomName" , params.get("roomName"));
            mv.addObject("roomNumber", params.get("roomNumber"));
            mv.setViewName("chat");
        return  mv;
    }
    @PostMapping("/insertDB")
    @ResponseBody
    public void insertDB(@RequestParam  HashMap<Object, Object> params){
        Map<String,Object> map = new HashMap<>();
        map.put("roomNumber",params.get("roomNumber"));
        map.put("name",params.get("userName"));
        map.put("message",params.get("msg"));
        map.put("time",params.get("time"));
        map.put("u_no",params.get("u_no"));
        messageService.insertMsg(map);
    }

}
