package com.project.moneycheck.Controller;

import com.project.moneycheck.dto.Spending;
import com.project.moneycheck.mapper.SpendingMapper;
import com.project.moneycheck.service.SpendingService;
import com.project.moneycheck.util.ExcelRead;
import com.project.moneycheck.util.ExcelReadOption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class SpendingController {
    private final SpendingMapper spendingMapper;
    private final SpendingService spendingService;

    @RequestMapping("/insert_spend")
    public String insert_spending(@RequestParam("book_no")String book_no ,Model model){
        List<Spending> spendingList = this.spendingService.spendingList(Integer.parseInt(book_no));
        model.addAttribute("spendingList",spendingList);

        return "/insert_spend";
    }

    @PostMapping("/insert_spending.do")
    public String spending_insert(@RequestParam("book_no")String book_no, Spending spending){
        System.out.println(spending.toString());

        spendingService.insert_spending(spending);

        return "redirect:/insert_spend?book_no=" + book_no;
    }
    @PostMapping("/delete_spending")
    public String delete_spending(@RequestParam("sp_no")String sp_no, @RequestParam("book_no") String book_no){

        spendingService.delete_spending(Integer.parseInt(sp_no));

        return "redirect:/insert_spend?book_no="+book_no;
    }

    @ResponseBody
    @PostMapping(value = "/excelUpload.do")
    public Map<String,Object> excelUpload(MultipartHttpServletRequest request, @RequestParam("book_no")String book_no) throws Exception{

        MultipartFile excelFile =request.getFile("excelFile");

        System.out.println("엑셀 파일 업로드");
        
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
        File destFile = new File("C:\\upload\\"+excelFile.getOriginalFilename());
//        try{
//            excelFile.transferTo(destFile);
//        }catch(IllegalStateException | IOException e){
////            throw new RuntimeException(e.getMessage(),e);
//            e.printStackTrace();
//        }

        ExcelReadOption excelReadOption = new ExcelReadOption();

        excelReadOption.setFilePath(destFile.getAbsolutePath()); // 파일경로
        System.out.println(destFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C","D","E","F"); //추출할 칼럼명
//        excelReadOption.setOutputColumns("A","B","C"); //추출할 칼럼명

        excelReadOption.setStartRow(2); //시작행

        List<Map<String,String>> excelContent = ExcelRead.read(excelReadOption);

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("excelContent",excelContent);
        paramMap.put("book_no",book_no);
        System.out.println(paramMap);

        try {
            spendingMapper.excelUpload(paramMap);

        }catch (Exception e){
            e.printStackTrace();
        }
        destFile.delete(); //업로드 진행 후 삭제

        return paramMap;

    }
}
