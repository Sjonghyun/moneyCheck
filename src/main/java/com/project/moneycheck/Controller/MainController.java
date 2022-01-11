package com.project.moneycheck.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.moneycheck.dto.*;
import com.project.moneycheck.mapper.ScheduleDao;
import com.project.moneycheck.mapper.SpendingMapper;
import com.project.moneycheck.service.IncomeService;
import com.project.moneycheck.service.MailService;
import com.project.moneycheck.service.SpendingService;
import com.project.moneycheck.service.UsersService;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
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
import java.util.*;

@Controller
@AllArgsConstructor
public class MainController {
    @Autowired
    SqlSession sqlSession;

    private IncomeService incomeService;
    private SpendingService spendingService;
    private final MailService mailService;
    private UsersService usersService;

    @GetMapping("/index")
    public String index(){
        return "/index";
    }

    @GetMapping("/sidebar")
    public String sidebar(Model model, @Param("book_no") String book_no){
        List<Users> usersList = usersService.loadUsers(Integer.parseInt(book_no));
        model.addAttribute("usersList",usersList);

        return "/sidebar";
    }

    @GetMapping("/main")
    public String main(Model model, HttpServletRequest request, DateData dateData, @Param("book_no") int book_no){

            List<Users> usersList = usersService.loadUsers(book_no);
            model.addAttribute("usersList",usersList);

            Calendar cal = Calendar.getInstance();
            DateData calendarData;

            // 검색 날짜
            if (dateData.getDate().equals("") && dateData.getMonth().equals("")) {
                dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)), String.valueOf(cal.get(Calendar.MONTH)),
                        String.valueOf(cal.get(Calendar.DATE)), null,null,null,null);
            }

            Map<String, Integer> today_info = dateData.today_info(dateData);
            List<DateData> dateList = new ArrayList<DateData>();

            // 검색 날짜 end
            ScheduleDao scheduleDao = sqlSession.getMapper(ScheduleDao.class);
            ArrayList<ScheduleDto> Schedule_list = scheduleDao.schedule_list(dateData,book_no);

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
            ArrayList<Spending> spending_list = spendingService.spend_list(dateData,book_no);
            Spending[][] spending_data_arr = new Spending[32][spending_list.size()];

            if (spending_list.isEmpty() != true) {
                int j = 0;
                for (int i = 0; i < spending_list.size(); i++) {
                    int date = Integer.parseInt(String.valueOf(spending_list.get(i).getSp_date()).substring(
                            String.valueOf(spending_list.get(i).getSp_date()).length() - 2,
                            String.valueOf(spending_list.get(i).getSp_date()).length()));
                    if (i > 0) {
                        int date_before = Integer.parseInt(String.valueOf(spending_list.get(i - 1).getSp_date())
                                .substring(String.valueOf(spending_list.get(i - 1).getSp_date()).length() - 2,
                                        String.valueOf(spending_list.get(i - 1).getSp_date()).length()));
                        if (date_before == date) {
                            j = j + 1;
                            spending_data_arr[date][j] = spending_list.get(i);
                        } else {
                            j = 0;
                            spending_data_arr[date][j] = spending_list.get(i);
                        }
                    } else {
                        spending_data_arr[date][j] = spending_list.get(i);
                    }
                }
            }
        ArrayList<Income> income_list = incomeService.income_list(dateData,book_no);
        Income[][] income_data_arr = new Income[32][income_list.size()];
        if (income_list.isEmpty() != true) {
            int j = 0;
            for (int i = 0; i < income_list.size(); i++) {
                int date = Integer.parseInt(String.valueOf(income_list.get(i).getIn_date()).substring(
                        String.valueOf(income_list.get(i).getIn_date()).length() - 2,
                        String.valueOf(income_list.get(i).getIn_date()).length()));
                if (i > 0) {
                    int date_before = Integer.parseInt(String.valueOf(income_list.get(i - 1).getIn_date())
                            .substring(String.valueOf(income_list.get(i - 1).getIn_date()).length() - 2,
                                    String.valueOf(income_list.get(i - 1).getIn_date()).length()));
                    if (date_before == date) {
                        j = j + 1;
                        income_data_arr[date][j] = income_list.get(i);
                    } else {
                        j = 0;
                        income_data_arr[date][j] = income_list.get(i);
                    }
                } else {
                    income_data_arr[date][j] = income_list.get(i);
                }
            }
        }
            for (int i = 1; i < today_info.get("start"); i++) {
                calendarData = new DateData(null, null, null, null,  null, null,null);
                dateList.add(calendarData);
            }
            for (int i = today_info.get("startDay"); i <= today_info.get("endDay"); i++) {
                ScheduleDto[] schedule_data_arr3 = new ScheduleDto[i];
                Spending[] spending_data_arr3 = new Spending[i];
                Income[] income_data_arr3 = new Income[i];

                schedule_data_arr3 = schedule_data_arr[i];
                spending_data_arr3 = spending_data_arr[i];
                income_data_arr3 = income_data_arr[i];

                if (i == today_info.get("today")) {
                    calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()),
                            String.valueOf(i), "today", schedule_data_arr3 , spending_data_arr3,income_data_arr3);
                } else {
                    calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()),
                            String.valueOf(i), "normal_date", schedule_data_arr3, spending_data_arr3,income_data_arr3);
                }
                dateList.add(calendarData);
            }
            int index = 7 - dateList.size() % 7;
            if (dateList.size() % 7 != 0) {
                for (int i = 0; i < index; i++) {
                    calendarData = new DateData(null, null, null, null,null,null,null);
                    dateList.add(calendarData);
                }
            }
            model.addAttribute("dateList", dateList);
            model.addAttribute("today_info", today_info);

        return "main";
    }
    @RequestMapping(value = "schedule_add.do", method = RequestMethod.GET)
    public String schedule_add(HttpServletRequest request, ScheduleDto scheduleDto,@Param("book_no") String book_no, RedirectAttributes rttr) {
        ScheduleDao scheduleDao = sqlSession.getMapper(ScheduleDao.class);
        int count = scheduleDao.before_schedule_add_search(scheduleDto);
        String message = "";
        String url = "redirect:/main?book_no="+book_no;
            scheduleDao.schedule_add(scheduleDto);
            message = "스케쥴이 등록되었습니다";
        rttr.addFlashAttribute("message", message);
        return url;
    }
    @RequestMapping(value = "/schedule_show", method = RequestMethod.GET)
    public String schedule_show(Model model,HttpServletRequest request, @RequestParam("schedule_idx") int idx, @RequestParam("book_no") int book_no, RedirectAttributes rttr) {
        ScheduleDao scheduleDao = sqlSession.getMapper(ScheduleDao.class);
        String url = "redirect:/main?book_no="+book_no;
        scheduleDao.get(idx,book_no);
        model.addAttribute("schedule_show",scheduleDao.get(idx,book_no));
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

    @RequestMapping("/login")
    public String login(DateData dateData ,Model model){

        Calendar cal = Calendar.getInstance();
        DateData calendarData;

        if (dateData.getDate().equals("") && dateData.getMonth().equals("")) {
            dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)), String.valueOf(cal.get(Calendar.MONTH)),
                    String.valueOf(cal.get(Calendar.DATE)), null,null,null,null);
        }
        Map<String, Integer> today_info = dateData.today_info(dateData);
        List<DateData> dateList = new ArrayList<DateData>();

        for (int i = 1; i < today_info.get("start"); i++) {
            calendarData = new DateData(null, null, null, null,  null, null,null);
            dateList.add(calendarData);
        }
        // 날짜 삽입
        for (int i = today_info.get("startDay"); i <= today_info.get("endDay"); i++) {
            if (i == today_info.get("today")) {
                calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()),
                        String.valueOf(i), "today",null,null,null);
            } else {
                calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()),
                        String.valueOf(i), "normal_date",null,null,null);
            }
            dateList.add(calendarData);
        }

        int index = 7 - dateList.size() % 7;
        if (dateList.size() % 7 != 0) {
            for (int i = 0; i < index; i++) {
                calendarData = new DateData(null, null, null, null,null,null,null);
                dateList.add(calendarData);
            }
        }

        model.addAttribute("dateList", dateList);
        model.addAttribute("today_info", today_info);
        return "/login";
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
        usersService.connectUsers(book_no,u_mail);

        return "/addUser";
    }

    @GetMapping("/totalChart")
    public String totalChart(@Param("book_no") String book_no, DateData dateData, Model model){

        List<Users> usersList = usersService.loadUsers(Integer.parseInt(book_no));
        model.addAttribute("usersList",usersList);

        Map<String, Integer> today_info = dateData.today_info(dateData);
        String y = today_info.get("search_year").toString();
        String ym ;
        if(today_info.get("search_month") < 10){
            ym = y +"-0"+ today_info.get("search_month").toString();

        }else {
            ym = y +"-"+ today_info.get("search_month").toString();
        }

        List<SpendingVO> yearSp = spendingService.yearSp(Integer.parseInt(book_no));
        List<SpendingVO> monthSp =  spendingService.monthSp(Integer.parseInt(book_no),y);
        List<SpendingVO> daySp = spendingService.DaySp(Integer.parseInt(book_no),ym);

        List<IncomeVO> yearIn = incomeService.yearIn(Integer.parseInt(book_no));
        List<IncomeVO> monthIn =  incomeService.monthIn(Integer.parseInt(book_no),y);
        List<IncomeVO> dayIn = incomeService.DayIn(Integer.parseInt(book_no),ym);
        int sum = 0;
        Gson gson = new Gson();
        JsonArray jArray = new JsonArray();
        Iterator<SpendingVO> YearSP = yearSp.iterator();
        while(YearSP.hasNext()){
            SpendingVO curVO = YearSP.next();
            JsonObject object = new JsonObject();
            String dates = curVO.getDates();
            String money = curVO.getMoney();

            object.addProperty("dates",dates);
            object.addProperty("money",money);
            jArray.add(object);
        }
        String year_sp = gson.toJson(jArray);
        model.addAttribute("yearSp",year_sp);
        jArray = new JsonArray();

        Iterator<SpendingVO> MonthSP = monthSp.iterator();
        while(MonthSP.hasNext()){
            SpendingVO curVO = MonthSP.next();
            JsonObject object = new JsonObject();
            String dates = curVO.getDates();
            String money = curVO.getMoney();
            sum = sum + Integer.parseInt(curVO.getMoney());

            object.addProperty("dates",dates);
            object.addProperty("money",money);
            jArray.add(object);
        }
        /////
        String month_sp = gson.toJson(jArray);
        model.addAttribute("monthSum",sum);
        sum = 0;
        model.addAttribute("monthSp",month_sp);
        jArray = new JsonArray();

        Iterator<SpendingVO> DaySP = daySp.iterator();
        while(DaySP.hasNext()){
            SpendingVO curVO = DaySP.next();
            JsonObject object = new JsonObject();
            String dates = curVO.getDates();
            String money = curVO.getMoney();
            sum = sum + Integer.parseInt(curVO.getMoney());

            object.addProperty("dates",dates);
            object.addProperty("money",money);
            jArray.add(object);
        }
        String day_sp = gson.toJson(jArray);
        model.addAttribute("daySum",sum);
        sum = 0;
        model.addAttribute("daySp",day_sp);
        jArray = new JsonArray();

        Iterator<IncomeVO> YearIn = yearIn.iterator();
        while(YearIn.hasNext()){
            IncomeVO curVO = YearIn.next();
            JsonObject object = new JsonObject();
            String dates = curVO.getDates();
            String money = curVO.getMoney();

            object.addProperty("dates",dates);
            object.addProperty("money",money);
            jArray.add(object);
        }
        String year_in = gson.toJson(jArray);
        model.addAttribute("yearIn",year_in);
        jArray = new JsonArray();

        Iterator<IncomeVO> MonthIn = monthIn.iterator();
        while(MonthIn.hasNext()){
            IncomeVO curVO = MonthIn.next();
            JsonObject object = new JsonObject();
            String dates = curVO.getDates();
            String money = curVO.getMoney();
            sum = sum + Integer.parseInt(curVO.getMoney());

            object.addProperty("dates",dates);
            object.addProperty("money",money);
            jArray.add(object);
        }
        model.addAttribute("monthInSum",sum);
        sum = 0;
        String month_in = gson.toJson(jArray);
        model.addAttribute("monthIn",month_in);
        jArray = new JsonArray();

        Iterator<IncomeVO> DayIn = dayIn.iterator();
        while(DayIn.hasNext()){
            IncomeVO curVO = DayIn.next();
            JsonObject object = new JsonObject();
            String dates = curVO.getDates();
            String money = curVO.getMoney();
            sum = sum + Integer.parseInt(curVO.getMoney());

            object.addProperty("dates",dates);
            object.addProperty("money",money);
            jArray.add(object);
        }
        model.addAttribute("dayInSum",sum);
        String day_in = gson.toJson(jArray);
        model.addAttribute("dayIn",day_in);


        model.addAttribute("today_info" ,today_info);

        return "/totalChart";
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(){
        return "/deleteUser";
    }
    @RequestMapping(value = "/deleteUser.do", method = RequestMethod.GET)
    public String deleteUsers(@RequestParam("u_no") String u_no){
        usersService.deleteSns(Integer.parseInt(u_no));
        usersService.deleteUser(Integer.parseInt(u_no));
        return "redirect:/logout";
    }
}
