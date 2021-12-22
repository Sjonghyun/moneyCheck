package com.project.moneycheck.Controller;

import com.project.moneycheck.dto.Spending;
import com.project.moneycheck.dto.Users;
import com.project.moneycheck.mapper.SpendingMapper;
import com.project.moneycheck.security.AuthValue;
import com.project.moneycheck.service.SpendingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class SpendingController {
    private final SpendingMapper spendingMapper;
    private final SpendingService spendingService;

    @RequestMapping("/insert_spend")
    public String insert_spending(@RequestParam("u_no")String u_no ,Model model){
        List<Spending> spendingList = this.spendingService.spendingList(Integer.parseInt(u_no));
        model.addAttribute("spendingList",spendingList);

        return "/insert_spend";
    }

    @PostMapping("/insert_spending.do")
    public String spending_insert(@RequestParam("u_no")String u_no, Spending spending){

        spendingService.insert_spending(spending);

        return "redirect:/insert_spend?u_no=" + u_no;
    }
    @PostMapping("/delete_spending")
    public String delete_spending(@RequestParam("sp_no")String sp_no, @RequestParam("u_no") String u_no){

        spendingService.delete_spending(Integer.parseInt(sp_no));

        return "redirect:/insert_spend?u_no="+u_no;
    }

    @ResponseBody
    @PostMapping(value = "/excelUpload.do")
    public String excelUpload(MultipartHttpServletRequest request) throws Exception{

        MultipartFile excelFile =request.getFile("excelFile");

        System.out.println("엑셀 파일 업로드");
        
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
        File destFile = new File("C:\\upload\\"+excelFile.getOriginalFilename());
        try{
            excelFile.transferTo(destFile);
        }catch(IllegalStateException | IOException e){
//            throw new RuntimeException(e.getMessage(),e);
            e.printStackTrace();
        }

        spendingService.excelUpload(destFile);

        destFile.delete(); //업로드 진행 후 삭제

        return "/main";
    }

    @RequestMapping("/excel")
    public String excel(){

        return "/excel";
    }
}
