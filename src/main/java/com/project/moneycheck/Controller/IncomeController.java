package com.project.moneycheck.Controller;

import com.project.moneycheck.dto.Income;
import com.project.moneycheck.service.IncomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@AllArgsConstructor
@Controller
public class IncomeController {
    private  final IncomeService incomeService;

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
}
