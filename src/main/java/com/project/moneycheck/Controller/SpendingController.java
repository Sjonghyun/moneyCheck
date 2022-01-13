package com.project.moneycheck.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.moneycheck.dto.DateData;
import com.project.moneycheck.dto.Spending;
import com.project.moneycheck.dto.SpendingVO;
import com.project.moneycheck.dto.Users;
import com.project.moneycheck.mapper.SpendingMapper;
import com.project.moneycheck.service.SpendingService;
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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class SpendingController {
    private final SpendingMapper spendingMapper;
    private final SpendingService spendingService;
    private final UsersService usersService;

    @RequestMapping("/insert_spend")
    public String insert_spending(@RequestParam("book_no")String book_no,DateData dateData ,Model model){
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

        List<Spending> spendingList = this.spendingService.spendingList(Integer.parseInt(book_no),ym);
        model.addAttribute("spendingList",spendingList);
        String spMoney = spendingService.spMoney(Integer.parseInt(book_no),ym);
        model.addAttribute("spMoney",spMoney);

        return "/insert_spend";
    }

    @PostMapping("/insert_spending.do")
    public String spending_insert(@RequestParam("book_no")String book_no, @RequestParam("year")String year, @RequestParam("month") String month, Spending spending){

        spendingService.insert_spending(spending);

        return "redirect:/insert_spend?book_no=" + book_no+"&year="+year+"&month="+month;
    }
    @PostMapping("/delete_spending")
    @ResponseBody
    public String delete_spending(@RequestParam("sp_no")String sp_no, @RequestParam("book_no") String book_no, @RequestParam("year")String year, @RequestParam("month") String month){
        spendingService.delete_spending(Integer.parseInt(sp_no));
        return "redirect:/insert_spend?book_no="+book_no+"&year="+year+"&month="+month;
    }

    @ResponseBody
    @PostMapping(value = "/excelUpload.do")
    public Map<String,Object> excelUpload(MultipartHttpServletRequest request, @RequestParam("book_no")String book_no) throws Exception{
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
            spendingMapper.excelUpload(paramMap);

        }catch (Exception e){
            e.printStackTrace();
        }
        if(destFile.exists()){
            destFile.delete();
        }

        return paramMap;
    }

    @GetMapping("/spChart")
    public String spendChart(@Param("book_no") String book_no, DateData dateData, Model model){
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

        Gson gson = new Gson();
        JsonArray jArray = new JsonArray();
        Iterator<SpendingVO> YearSP = yearSp.iterator();
        int sum = 0;
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
        sum = 0;
        while(MonthSP.hasNext()){
            SpendingVO curVO = MonthSP.next();
            JsonObject object = new JsonObject();
            String dates = curVO.getDates();
            String money = curVO.getMoney();
            sum = sum + Integer.parseInt(curVO.getMoney());

            object.addProperty("dates",dates);
            object.addProperty("money",money);
            object.addProperty("sum", sum);
            jArray.add(object);
        }
        String month_sp = gson.toJson(jArray);
        model.addAttribute("monthSum",sum);
        model.addAttribute("monthSp",month_sp);
        jArray = new JsonArray();

        Iterator<SpendingVO> DaySP = daySp.iterator();
        sum = 0;
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
        model.addAttribute("daySp",day_sp);

        model.addAttribute("today_info" ,today_info);

        return "/spChart";
    }


}
