package com.minh.labweek05.controller;

import com.minh.labweek05.model.Account;
import com.minh.labweek05.model.Candidate;
import com.minh.labweek05.model.Company;
import com.minh.labweek05.repositories.CandidateRepository;
import com.minh.labweek05.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @GetMapping(value = {"/register"})
    public String register(){
        return "register";
    }
    @PostMapping(value = {"/register"})
    public String registerPost(@RequestParam("accountType") String accountType,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password, RedirectAttributes redirectAttributes){
        if(accountType.equals("candidate")){
            redirectAttributes.addAttribute("email",email);
            redirectAttributes.addAttribute("password",password);
            redirectAttributes.addAttribute("accountType",accountType);
            return "redirect:/register/candidate";
        }
        else if(accountType.equals("company")){
            redirectAttributes.addAttribute("email",email);
            redirectAttributes.addAttribute("password",password);
            redirectAttributes.addAttribute("accountType",accountType);
            return "redirect:/register/company";
        }

        return "register";
    }
    @GetMapping(value = {"/register/candidate"})
    public String candidateRegister(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("accountType") String accountType, Model model){
        model.addAttribute("email",email);
        model.addAttribute("password",password);
        model.addAttribute("candidate", new Candidate());
        return "candidateRegister";
    }
    @PostMapping(value = {"/register/candidate"})
    public String candidateRegisterPost(@RequestParam("email") String email,@RequestParam("password") String password,@ModelAttribute("candidate") Candidate candidate){
        candidate.setAccount(new Account(0,email,password,"candidate"));
        if(candidateRepository.save(candidate)!=null){
            return "login";
        }
        return "candidateRegister";
    }

@GetMapping(value = {"/register/company"})
public String companyRegister(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("accountType") String accountType, Model model){
    model.addAttribute("email",email);
    model.addAttribute("password",password);
    model.addAttribute("company", new Company());
    return "companyRegister";
}
    @PostMapping(value = {"/register/company"})
    public String companyRegisterPost(@RequestParam("email") String email,@RequestParam("password") String password,@ModelAttribute("company") Company company){
        company.setAccount(new Account(email,password,"company"));
        if(companyRepository.save(company)!=null){
            return "login";
        }
        return "companyRegister";
    }
}
