package com.project.moneycheck.Controller;

import com.project.moneycheck.dto.DateData;
import com.project.moneycheck.dto.ScheduleDto;
import com.project.moneycheck.dto.Spending;
import com.project.moneycheck.mapper.ScheduleDao;
import com.project.moneycheck.service.IncomeService;
import com.project.moneycheck.service.MailService;
import com.project.moneycheck.service.SpendingService;
import com.project.moneycheck.service.UsersService;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MainController {
    @Autowired
    SqlSession sqlSession;

    private SpendingService spendingService;
    private final MailService mailService;
    private UsersService usersService;

    @GetMapping("/main")
    public String main(Model model, HttpServletRequest request, DateData dateData){

            Calendar cal = Calendar.getInstance();
            DateData calendarData;
            // 검색 날짜
            if (dateData.getDate().equals("") && dateData.getMonth().equals("")) {
                dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)), String.valueOf(cal.get(Calendar.MONTH)),
                        String.valueOf(cal.get(Calendar.DATE)), null, null);
            }

            Map<String, Integer> today_info = dateData.today_info(dateData);
            List<DateData> dateList = new ArrayList<DateData>();

            // 검색 날짜 end
            ScheduleDao scheduleDao = sqlSession.getMapper(ScheduleDao.class);
            ArrayList<ScheduleDto> Schedule_list = scheduleDao.schedule_list(dateData);
            // 달력데이터에 넣기 위한 배열 추가
            ScheduleDto[][] schedule_data_arr = new ScheduleDto[32][4];
            if (Schedule_list.isEmpty() != true) {
                int j = 0;
                for (int i = 0; i < Schedule_list.size(); i++) {
                    int date = Integer.parseInt(String.valueOf(Schedule_list.get(i).getSchedule_date()).substring(
                            String.valueOf(Schedule_list.get(i).getSchedule_date()).length() - 2,
                            String.valueOf(Schedule_list.get(i).getSchedule_date()).length()));
                    if (i > 0) {
                        int date_before = Integer.parseInt(String.valueOf(Schedule_list.get(i - 1).getSchedule_date())
                                .substring(String.valueOf(Schedule_list.get(i - 1).getSchedule_date()).length() - 2,
                                        String.valueOf(Schedule_list.get(i - 1).getSchedule_date()).length()));
                        if (date_before == date) {
                            j = j + 1;
                            schedule_data_arr[date][j] = Schedule_list.get(i);
                        } else {
                            j = 0;
                            schedule_data_arr[date][j] = Schedule_list.get(i);
                        }
                    } else {
                        schedule_data_arr[date][j] = Schedule_list.get(i);
                    }
                }
            }
            // 실질적인 달력 데이터 리스트에 데이터 삽입 시작.
            // 일단 시작 인덱스까지 아무것도 없는 데이터 삽입
            for (int i = 1; i < today_info.get("start"); i++) {
                calendarData = new DateData(null, null, null, null, null);
                dateList.add(calendarData);
            }
            // 날짜 삽입
            for (int i = today_info.get("startDay"); i <= today_info.get("endDay"); i++) {
                ScheduleDto[] schedule_data_arr3 = new ScheduleDto[4];
                schedule_data_arr3 = schedule_data_arr[i];
                if (i == today_info.get("today")) {
                    calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()),
                            String.valueOf(i), "today", schedule_data_arr3);
                } else {
                    calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()),
                            String.valueOf(i), "normal_date", schedule_data_arr3);
                }
                dateList.add(calendarData);
            }
            // 달력 빈 곳 빈 데이터로 삽입
            int index = 7 - dateList.size() % 7;
            if (dateList.size() % 7 != 0) {
                for (int i = 0; i < index; i++) {
                    calendarData = new DateData(null, null, null, null, null);
                    dateList.add(calendarData);
                }
            }
            // 배열에 담음

            model.addAttribute("dateList", dateList); // 날짜 데이터 배열
            model.addAttribute("today_info", today_info);

        return "main";
    }
    @RequestMapping(value = "schedule_add.do", method = RequestMethod.GET)
    public String schedule_add(HttpServletRequest request, ScheduleDto scheduleDto, RedirectAttributes rttr) {
        ScheduleDao scheduleDao = sqlSession.getMapper(ScheduleDao.class);
        int count = scheduleDao.before_schedule_add_search(scheduleDto);
        String message = "";
        String url = "redirect:main";
        if (count >= 4) {
            message = "스케쥴은 최대 4개만 등록 가능합니다.";
        } else {
            scheduleDao.schedule_add(scheduleDto);
            message = "스케쥴이 등록되었습니다";
        }
        rttr.addFlashAttribute("message", message);
        return url;
    }
    @RequestMapping(value = "/schedule_show", method = RequestMethod.GET)
    public String schedule_show(Model model,HttpServletRequest request, @RequestParam("schedule_idx") int idx, RedirectAttributes rttr) {
        ScheduleDao scheduleDao = sqlSession.getMapper(ScheduleDao.class);
        String url = "redirect:/main";
        scheduleDao.get(idx);
        model.addAttribute("schedule_show",scheduleDao.get(idx));
        return null;
    }

    @RequestMapping(value = "modify.do", method = RequestMethod.GET)
    public String schedule_modify(Model model,HttpServletRequest request, ScheduleDto scheduleDto, RedirectAttributes rttr) {
        ScheduleDao scheduleDao = sqlSession.getMapper(ScheduleDao.class);
        scheduleDao.update(scheduleDto);
        model.addAttribute("schedule_modify",scheduleDao.update(scheduleDto));
        return "/modify";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String schedule_delete(Model model,HttpServletRequest request, ScheduleDto scheduleDto, RedirectAttributes rttr) {
        ScheduleDao scheduleDao = sqlSession.getMapper(ScheduleDao.class);
        scheduleDao.delete(scheduleDto);
        model.addAttribute("schedule_delete",scheduleDao.delete(scheduleDto));
        return null;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/sessionCheck")
    public String userInfo(){

        return "userInfo";
    }
    @PostMapping("/download.do")
    public String excelDownload(@RequestParam("book_no")String book_no, HttpServletResponse response) throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("업로드 양식");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;
        sheet.setColumnWidth((short)4, (short)3500); //열 너비

        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("내역일");
        cell = row.createCell(1);
        cell.setCellValue("내역");
        cell = row.createCell(2);
        cell.setCellValue("금액");
        cell = row.createCell(3);
        cell.setCellValue("분류");
        cell = row.createCell(4);
        cell.setCellValue("출금/입금통장");
        cell = row.createCell(5);
        cell.setCellValue("메모");

        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=moneyCheckForm.xlsx");

        try {
           wb.write(response.getOutputStream());
           wb.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
    @RequestMapping("/excel")
    public String excel(@RequestParam("book_no")String book_no){

        return "/excel";
    }
    @RequestMapping("/excelIncome")
    public String excelIncome(@RequestParam("book_no")String book_no){

        return "/excelIncome";
    }
    @GetMapping("/invite")
    public String invite(){
        return "/invite";
    }

    @ResponseBody
    @PostMapping("/invite.do")
    public int execMail(HttpServletRequest request, String u_mail,String name,String book_no) {
        int num = usersService.userMail(u_mail);
        if(num == 1){
            mailService.mailSend(request, u_mail,name,book_no);
        }else{
            System.out.println("아이디가 없습니다.");
        }
        return num;
    }

    @GetMapping("/addUser")
    public String connectUsers(@RequestParam("book_no")String book_no, @RequestParam("u_mail") String u_mail){
        System.out.println(book_no);
        System.out.println(u_mail);
        usersService.connectUsers(book_no,u_mail);

        return "/addUser";
    }
}
