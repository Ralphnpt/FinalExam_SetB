package com.example.ralph300341112.web;

import com.example.ralph300341112.entities.Salesman;
import com.example.ralph300341112.respository.SalesmanRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

//https://github.com/Ralphnpt/FinalExam_SetB.git
@SessionAttributes({"a", "e"})
@Controller
@AllArgsConstructor

public class SalesmanController {
    @Autowired
    private SalesmanRepository salesmanRepository;
    static int num = 0;

    @GetMapping(path = "/index")
    public String salesmans(Model model, @RequestParam(name = "keyword", defaultValue = "")
    String keyword) {
        List<Salesman> salesmans;
        if (keyword.isEmpty()) {
            salesmans = salesmanRepository.findAll();
        } else {
            long key = Long.parseLong(keyword);
            salesmans = salesmanRepository.findSalesmanById(key);
        }
        model.addAttribute("listSalesmans", salesmans);
        return "salesmans";
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        salesmanRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/formSalesmans")
    public String formSalesmans(Model model) {
        model.addAttribute("salesman", new Salesman());
        return "formSalesmans";
    }

    @PostMapping(path = "/save")
    public String save(Model model, Salesman salesman, BindingResult bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "formSalesmans";
        } else {
            salesmanRepository.save(salesman);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
            }
            return "redirect:index";
        }
    }

    @GetMapping("/editSalesmans")
    public String editSalesmans(Model model, Long id, HttpSession session) {
        num = 2;
        session.setAttribute("info", 0);
        Salesman salesman = salesmanRepository.findById(id).orElse(null);
        if (salesman == null) throw new RuntimeException("Salesman does not exist");
        model.addAttribute("salesman", salesman);
        return "editSalesmans";
    }

    @GetMapping(path = "/")
    public String salesmans2(Model model, ModelMap mm, @RequestParam(name = "keyword", defaultValue = "") String keyword, HttpSession session)
    {
        List<Salesman> salesmans;
        if (keyword.isEmpty()) {
            salesmans = salesmanRepository.findAll();
        } else {
            mm.put("e", 0);
            mm.put("a", 0);
            long key = Long.parseLong(keyword);
            salesmans = salesmanRepository.findSalesmanById(key);
        }
        model.addAttribute("listSalesmans", salesmans);
        return "salesmans";
    }
}
