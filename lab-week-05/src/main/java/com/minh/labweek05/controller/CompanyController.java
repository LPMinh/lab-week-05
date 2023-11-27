package com.minh.labweek05.controller;

import com.minh.labweek05.model.*;
import com.minh.labweek05.repositories.CandidateRepository;
import com.minh.labweek05.repositories.CompanyRepository;
import com.minh.labweek05.repositories.ExperienceRepository;
import com.minh.labweek05.repositories.JobRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Controller

public class CompanyController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    @GetMapping("/company")
    public String company(Model model, HttpSession session, @RequestParam(name = "page", defaultValue = "1") int page,
                            @RequestParam(name = "size", defaultValue = "6") int size) {

        Account account = (Account) session.getAttribute("account");
        Company company= companyRepository.findCompanyByAccount_Id(account.getId());
        model.addAttribute("company", company);
        List<Candidate> candidateAll=candidateRepository.findAll();

        int totalCandidates = candidateAll.size();
        System.out.println(totalCandidates);
        int totalPages = (int) Math.ceil((double) totalCandidates / size);
        int maxPagesToShow = 5;
        int startPage = Math.max(1, page - maxPagesToShow / 2);
        int endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);

        int startItem = (page - 1) * size;
        int endItem = Math.min(startItem + size, totalCandidates);
        List<Candidate> paginatedCandidate = candidateAll.subList(startItem, endItem);
        model.addAttribute("candidates", new PageImpl<>(paginatedCandidate, PageRequest.of(page - 1, size), totalPages));
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "company/company";
    }

    @PostMapping("/add-job")
    public String addJob(@RequestParam("companyId") Long id, @RequestParam("jobName") String name, @RequestParam("jobDescription") String description, HttpSession session){

        Account account= (Account) session.getAttribute("account");
        Company company=companyRepository.findCompanyByAccount_Id(account.getId());
        Job job=new Job(company,name,description,new ArrayList<JobSkill>());
        company.getJobs().add(job);
        if(companyRepository.save(company)!=null){
           System.out.println("success");
        }

        return "redirect:/profile-company/"+id;
    }

}
