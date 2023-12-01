package com.minh.labweek05.controller;

import com.minh.labweek05.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmalController {
    @Autowired private EmailService emailService;

    @PostMapping("/send-email")
    public String sendEmail(@ModelAttribute com.minh.labweek05.model.Email email, @RequestParam("candidateId") Long candidateId,@RequestParam("candidateEmail") String candidateEmail){

        email.setTo(candidateEmail);
        emailService.sendMail(email);

        return "redirect:/profile/"+candidateId+"";
    }

}
