package com.project.moneycheck.service;

import com.project.moneycheck.util.MailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;


@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String FROM_ADDRESS = "ghdi95@gmail.com";


    public boolean mailSend(HttpServletRequest request, String u_mail ,String name, String book_no) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);


            // 받는 사람
            mailHandler.setTo(u_mail);
            // 보내는 사람
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            // 제목
            mailHandler.setSubject("moneyCheck 초대메일 입니다.");
            // HTML Layout
            String htmlContent = "<p>"+name+"님의 초대 받으셨습니다.</p>"
                    +"<p>초대에 수락하시면 함께 이용할 수 있습니다.</p>"
                    +"<p>초대에 수락하시길 원하시면 수락하기를 클릭해주세요.<a href='http://localhost:8080/addUser?book_no="+book_no+"&u_mail="+u_mail+"'>수락하기</a></p>";

            mailHandler.setText(htmlContent, true);

            mailHandler.send();

            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
