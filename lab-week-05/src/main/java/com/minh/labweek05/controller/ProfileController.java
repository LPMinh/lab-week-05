package com.minh.labweek05.controller;

import com.minh.labweek05.model.Account;
import com.minh.labweek05.model.Candidate;
import com.minh.labweek05.model.Company;
import com.minh.labweek05.model.Email;
import com.minh.labweek05.repositories.CandidateRepository;
import com.minh.labweek05.repositories.CompanyRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable("id") Long id, Model model, HttpSession session){
        Email email=new Email();
        model.addAttribute("email",email);
        Candidate candidate=candidateRepository.findById(id).get();
        model.addAttribute("candidate",candidate);
        Account account= (Account) session.getAttribute("account");
        if(account.getRole().equals("candidate")){
            return "candidates/Profile";
        }else if(account.getRole().equals("company")){
            return "company/view_candidate_profile";
        }
        return "redirect:/login";
    }
    @GetMapping("/profile-company/{id}")
    public String profile_company(@PathVariable("id") Long id, Model model, HttpSession session){
        Company company=companyRepository.findById(id).get();
        model.addAttribute("company",company);
        Account account= (Account) session.getAttribute("account");
        if(account.getRole().equals("candidate")){
            return "company/profile-company";
        }else if(account.getRole().equals("company")){
            return "company/profile-company";
        }
        return "redirect:/login";
    }


}
