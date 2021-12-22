package com.project.moneycheck.service;

import com.project.moneycheck.dto.Spending;
import com.project.moneycheck.mapper.SpendingMapper;
import com.project.moneycheck.util.ExcelRead;
import com.project.moneycheck.util.ExcelReadOption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class SpendingService {
    private final SpendingMapper spendingMapper;

    public void insert_spending(Spending spending){
        spendingMapper.insert_spending(spending);
    }

    public List<Spending> spendingList(int u_no){

        return spendingMapper.spendingList(u_no);
    }

    public void delete_spending(int sp_no){
        spendingMapper.delete_spending(sp_no);
    }

    public String spendCount(int u_no, String date){

        return spendingMapper.spendCount(u_no, date);
    }

    public List<Map<String, String>> excelUpload(File destFile) throws Exception{

        ExcelReadOption excelReadOption = new ExcelReadOption();

        excelReadOption.setFilePath(destFile.getAbsolutePath()); // 파일경로
//        excelReadOption.setOutputColumns("A","B","C","D","E","F"); //추출할 칼럼명
        excelReadOption.setOutputColumns("A","B","C"); //추출할 칼럼명

        excelReadOption.setStartRow(2); //시작행

        List<Map<String,String>> excelContent = ExcelRead.read(excelReadOption);



        for(Map<String, String> article: excelContent){
            System.out.print(article.get("A")+" ");
            System.out.print(article.get("B")+" ");
            System.out.print(article.get("C")+" ");
//            System.out.print(article.get("D")+" ");
//            System.out.print(article.get("E")+" ");
//            System.out.print(article.get("F")+" ");
            System.out.println();
        }
        System.out.println(excelContent.toArray());

        return spendingMapper.excelUpload(destFile);
    }


}
