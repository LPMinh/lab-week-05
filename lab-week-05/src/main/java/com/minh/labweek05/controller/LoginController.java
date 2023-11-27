package com.minh.labweek05.controller;

import com.minh.labweek05.model.Account;
import com.minh.labweek05.model.Candidate;
import com.minh.labweek05.repositories.AccountRepository;
import com.minh.labweek05.repositories.CandidateRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping(value = {"/login","/"})
    public String login(){
        return "login";
    }
    @GetMapping("/handleLogin")
    public String handleLogin(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model){
        if (accountRepository.findByEmailAndPassword(email,password).isPresent()){
            Account account = accountRepository.findByEmailAndPassword(email,password).get();
            Candidate candidate=candidateRepository.findCandidateByAccount_Id(account.getId());
            model.addAttribute("candidate",candidate);
            session.setAttribute("candidate",candidate);
            if (account.getRole().equals("candidate")){
                session.setAttribute("account",account);
                return "redirect:/candidate";
            }else if (account.getRole().equals("company")){
                session.setAttribute("account",account);
                return "redirect:/company";
            }
        }
        return "redirect:/login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("account");
        return "redirect:/login";
    }
}
