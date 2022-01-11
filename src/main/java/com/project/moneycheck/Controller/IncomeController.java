package com.project.moneycheck.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.moneycheck.dto.DateData;
import com.project.moneycheck.dto.Income;
import com.project.moneycheck.dto.IncomeVO;
import com.project.moneycheck.dto.Users;
import com.project.moneycheck.mapper.IncomeMapper;
import com.project.moneycheck.service.IncomeService;
import com.project.moneycheck.service.UsersService;
import com.project.moneycheck.util.ExcelRead;
import com.project.moneycheck.util.ExcelReadOption;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class IncomeController {
    private  final IncomeService incomeService;
    private final IncomeMapper incomeMapper;
    private final UsersService usersService;

    @RequestMapping("/insert_income")
    public String insert_income(@RequestParam("book_no") String book_no, DateData dateData,Model model){
        List<Users> usersList = usersService.loadUsers(Integer.parseInt(book_no));
        model.addAttribute("usersList",usersList);

        Map<String, Integer> today_info = dateData.today_info(dateData);
        model.addAttribute("today_info",today_info);
        String ym ;
        if(today_info.get("search_month") < 10){
            ym = today_info.get("search_year").toString() +"-0"+ today_info.get("search_month").toString();

        }else {
            ym = today_info.get("search_year").toString() + "-"+ today_info.get("search_month").toString();
        }
        List<Income> incomeList = this.incomeService.incomeList(Integer.parseInt(book_no),ym);
        model.addAttribute("incomeList",incomeList);
        String inMoney = incomeService.inMoney(Integer.parseInt(book_no),ym);
        model.addAttribute("inMoney",inMoney);


        return "/insert_income";
    }
    @PostMapping("/insert_income.do")
    public String income_insert(HttpServletRequest request, Income income){
        incomeService.insert_income(income);
        String book_no = request.getParameter("book_no");
        int year = Integer.parseInt(request.getParameter("year"));
        int month =  Integer.parseInt(request.getParameter("month"));
        return "redirect:/insert_income?book_no="+ book_no+"&year="+year+"&month="+month;
    }
    @PostMapping("/delete_income")
    @ResponseBody
    public String delete_income(@RequestParam("in_no") String in_no, @RequestParam("book_no") String book_no, @RequestParam("year")String year, @RequestParam("month") String month){
        System.out.println("@@@@@@@@@@@@"+in_no);
        incomeService.delete_income(Integer.parseInt(in_no));

        return "redirect:/insert_income?book_no="+ book_no+"&year="+year+"&month="+month;
    }
    @ResponseBody
    @PostMapping(value = "/income_excelUpload.do")
    public Map<String,Object> income_excelUpload(MultipartHttpServletRequest request, @RequestParam("book_no")String book_no) throws Exception{

        MultipartFile excelFile =request.getFile("excelFile");

        System.out.println("엑셀 파일 업로드");

        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
        File destFile = new File(request.getRealPath("/upload")+ File.separator +excelFile.getOriginalFilename());
        try{
            excelFile.transferTo(destFile);
        }catch(IllegalStateException | IOException e){
            e.printStackTrace();
        }

        ExcelReadOption excelReadOption = new ExcelReadOption();

        excelReadOption.setFilePath(destFile.getAbsolutePath()); // 파일경로
        excelReadOption.setOutputColumns("A","B","C","D","E","F"); //추출할 칼럼명

        excelReadOption.setStartRow(2); //시작행

        List<Map<String,String>> excelContent = ExcelRead.read(excelReadOption);

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("excelContent",excelContent);
        paramMap.put("book_no",book_no);

        try {
            incomeMapper.excelUpload(paramMap);

        }catch (Exception e){
            e.printStackTrace();
        }
        if(destFile.exists()){
            destFile.delete();
        }

        return paramMap;

    }

    @GetMapping("/inChart")
    public String incomeChart(@Param("book_no") String book_no, DateData dateData, Model model){
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

        List<IncomeVO> yearIn = incomeService.yearIn(Integer.parseInt(book_no));
        List<IncomeVO> monthIn =  incomeService.monthIn(Integer.parseInt(book_no),y);
        List<IncomeVO> dayIn = incomeService.DayIn(Integer.parseInt(book_no),ym);

        Gson gson = new Gson();
        JsonArray jArray = new JsonArray();
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
        int sum = 0;
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
        String month_in = gson.toJson(jArray);
        model.addAttribute("monthInSum",sum);
        model.addAttribute("monthIn",month_in);
        jArray = new JsonArray();

        Iterator<IncomeVO> DayIn = dayIn.iterator();
        sum = 0;
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
        String day_in = gson.toJson(jArray);
        model.addAttribute("dayInSum",sum);
        model.addAttribute("dayIn",day_in);

        model.addAttribute("today_info" ,today_info);

        return "/inChart";
    }
}
