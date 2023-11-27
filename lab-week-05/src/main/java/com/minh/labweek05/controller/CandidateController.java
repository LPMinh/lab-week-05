package com.minh.labweek05.controller;

import com.minh.labweek05.enums.SkillLevel;
import com.minh.labweek05.model.*;
import com.minh.labweek05.repositories.CandidateRepository;
import com.minh.labweek05.repositories.ExperienceRepository;
import com.minh.labweek05.repositories.JobRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    @GetMapping("/candidate")
    public String candidate(Model model, HttpSession session, @RequestParam(name = "page", defaultValue = "1") int page,
                            @RequestParam(name = "size", defaultValue = "6") int size) {

        Account account = (Account) session.getAttribute("account");
        SkillLevel[] skillLevels = SkillLevel.values();
        model.addAttribute("skillLevels", skillLevels);
        Candidate candidate = candidateRepository.findCandidateByAccount_Id(account.getId());
        model.addAttribute("candidate", candidate);
        List<Job> jobsNearYou = jobRepository.findJobNearMe(candidate.getAddress().getStreet(),
                candidate.getAddress().getCity(), candidate.getAddress().getCountry(), candidate.getAddress().getZipCode());

        List<Job> jobsBySkill = new ArrayList<>();
        for (CandidateSkill candidateSkill : candidate.getCandidateSkills()) {
            jobsBySkill.addAll(jobRepository.findJobBySkill(candidateSkill.getSkill().getSkillName(),
                    candidateSkill.getSkill().getSkillDescription(), candidateSkill.getSkill().getType()));
        }

        List<Job> jobSuggest = Stream.concat(jobsNearYou.stream(), jobsBySkill.stream()).distinct().toList();
        List<Job> jobAll=jobRepository.findAll();
        int totalJobs = jobAll.size();
        int totalPages = (int) Math.ceil((double) totalJobs / size);

        int maxPagesToShow = 5;
        int startPage = Math.max(1, page - maxPagesToShow / 2);
        int endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);

        int startItem = (page - 1) * size;
        int endItem = Math.min(startItem + size, totalJobs);

        List<Job> paginatedJobs = jobAll.subList(startItem, endItem);

        model.addAttribute("jobPage", new PageImpl<>(paginatedJobs, PageRequest.of(page - 1, size), totalJobs));
        model.addAttribute("jobsNearYou", jobsNearYou);
        model.addAttribute("jobsBySkill", jobsBySkill);
        model.addAttribute("jobSuggest", jobSuggest);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "candidates/candidate";
    }
    @GetMapping("/candidates/deleteExperience/{id}")
    public String deleteExperience(@PathVariable("id") Long id, HttpSession session){
        Candidate candidate= (Candidate) session.getAttribute("candidate");

        Optional<Candidate> candidateOptional=candidateRepository.findById(candidate.getId());
        if (candidateOptional.isPresent()){
            Candidate candidate1=candidateOptional.get();
            candidate1.getExperience().removeIf(experience -> experience.getId()==id);
            candidateRepository.save(candidate1);
        }
        return "redirect:/profile/"+candidate.getId();
    }
    @GetMapping("/candidates/editExperience/{id}")
    public String editExperience(@PathVariable("id") long experienceId, Model model,HttpSession session) {
        // Retrieve the experience by ID from the database (you need to implement this method)
        Experience experience = experienceRepository.findById(experienceId).orElse(null);
        Candidate candidate= (Candidate) session.getAttribute("candidate");
        if (experience != null) {
            model.addAttribute("experience", experience);
            return "candidates/edit-experience";
        } else {

            return "redirect:/profile/"+candidate.getId(); // Redirect to the candidate profile page
        }
    }

    @PostMapping("/saveExperience")
    public String saveExperience(@ModelAttribute Experience experience,HttpSession session) {
        Candidate candidate= (Candidate) session.getAttribute("candidate");
        // Save the updated experience to the database (you need to implement this method)
        experienceRepository.save(experience);
        return "redirect:/profile/"+candidate.getId(); // Redirect to the candidate profile page
    }
    @GetMapping("/search-job")
    public String searchJob(@RequestParam("searchTerm")String query,@RequestParam("level")String skillLevel,Model model,HttpSession session,@RequestParam(name = "page", defaultValue = "1") int page,
                            @RequestParam(name = "size", defaultValue = "6") int size){
        SkillLevel skillLevel1=SkillLevel.valueOf(skillLevel);
        System.out.println("search page");
        List<Job> jobs=jobRepository.findJobBySkillAndLevel(query,skillLevel1);
        Account account = (Account) session.getAttribute("account");
        SkillLevel[] skillLevels = SkillLevel.values();
        model.addAttribute("skillLevels", skillLevels);
        Candidate candidate = candidateRepository.findCandidateByAccount_Id(account.getId());
        model.addAttribute("candidate", candidate);




        int totalJobs = jobs.size();
        int totalPages = (int) Math.ceil((double) totalJobs / size);

        int maxPagesToShow = 5;
        int startPage = Math.max(1, page - maxPagesToShow / 2);
        int endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);

        int startItem = (page - 1) * size;
        int endItem = Math.min(startItem + size, totalJobs);

        List<Job> paginatedJobs = jobs.subList(startItem, endItem);

        model.addAttribute("jobPage", new PageImpl<>(paginatedJobs, PageRequest.of(page - 1, size), totalJobs));
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "candidates/Search-page";

    }

    }


