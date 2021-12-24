package com.project.moneycheck.Controller;

import com.project.moneycheck.dto.Income;
import com.project.moneycheck.mapper.IncomeMapper;
import com.project.moneycheck.service.IncomeService;
import com.project.moneycheck.util.ExcelRead;
import com.project.moneycheck.util.ExcelReadOption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class IncomeController {
    private  final IncomeService incomeService;
    private final IncomeMapper incomeMapper;

    @RequestMapping("/insert_income")
    public String insert_income(@RequestParam("u_no") String u_no, Model model){

        List<Income> incomeList = this.incomeService.incomeList(Integer.parseInt(u_no));
        model.addAttribute("incomeList",incomeList);

        return "/insert_income";
    }
    @PostMapping("/insert_income.do")
    public String income_insert(@RequestParam("u_no") String u_no,Income income){
        incomeService.insert_spending(income);
        return "redirect:/insert_income?u_no="+ u_no;
    }
    @PostMapping("/delete_income")
    public String delete_income(@RequestParam("in_no")String in_no, @RequestParam("u_no") String u_no){
        incomeService.delete_income(Integer.parseInt(in_no));
        //여기서부터 하면됨.
        return "redirect:/insert_income?u_no="+ u_no;
    }
    @ResponseBody
    @PostMapping(value = "/income_excelUpload.do")
    public Map<String,Object> income_excelUpload(MultipartHttpServletRequest request, @RequestParam("u_no")String u_no) throws Exception{

        MultipartFile excelFile =request.getFile("excelFile");

        System.out.println("엑셀 파일 업로드");

        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
        File destFile = new File("C:\\upload\\"+excelFile.getOriginalFilename());
        try{
            excelFile.
                    transferTo(destFile);
        }catch(IllegalStateException | IOException e){
//            throw new RuntimeException(e.getMessage(),e);
            e.printStackTrace();
        }

        ExcelReadOption excelReadOption = new ExcelReadOption();

        excelReadOption.setFilePath(destFile.getAbsolutePath()); // 파일경로
        excelReadOption.setOutputColumns("A","B","C","D","E","F"); //추출할 칼럼명
//        excelReadOption.setOutputColumns("A","B","C"); //추출할 칼럼명

        excelReadOption.setStartRow(2); //시작행

        List<Map<String,String>> excelContent = ExcelRead.read(excelReadOption);

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("excelContent",excelContent);
        paramMap.put("u_no",u_no);
        System.out.println(paramMap);

        try {
            incomeMapper.excelUpload(paramMap);

        }catch (Exception e){
            e.printStackTrace();
        }
//        destFile.delete(); //업로드 진행 후 삭제

        return paramMap;

    }
}
