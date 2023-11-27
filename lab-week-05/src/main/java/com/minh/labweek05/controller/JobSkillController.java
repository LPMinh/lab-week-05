package com.minh.labweek05.controller;

import com.minh.labweek05.enums.SkillLevel;
import com.minh.labweek05.enums.SkillType;
import com.minh.labweek05.model.Company;
import com.minh.labweek05.model.Job;
import com.minh.labweek05.model.JobSkill;
import com.minh.labweek05.model.Skill;
import com.minh.labweek05.repositories.JobRepository;
import com.minh.labweek05.repositories.JobSkillRepository;
import com.minh.labweek05.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobSkillController {
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/jobskill/{id}")
    public String getJobSkillById(@PathVariable(value = "id") Long id, Model model) {
        Job job = jobRepository.findById(id).get();
        model.addAttribute("job", job);
        JobSkill jobSkill = new JobSkill();

        return "company/add-job-skill";
    }
    @PostMapping("/add-job-skill")
    public String addJobSkill(@RequestParam("jobId") long jobid,@RequestParam("skillName") String skillName, @RequestParam("skillDescription") String skillDescription, @RequestParam("moreInfo") String moreInfo, @RequestParam("skillLevel") SkillLevel skillLevel,@RequestParam("type") String type) {
        Job job = jobRepository.findById(jobid).get();
        Skill skill = new Skill(SkillType.valueOf(type),skillName , skillDescription);
        skillRepository.save(skill);
        JobSkill jobSkill = new JobSkill(skillLevel, skill, job);
        job.getJobSkills().add(jobSkill);
        if (jobRepository.save(job) != null) {
            System.out.println("Add job skill successfully");
            return "redirect:/jobskill/"+jobid;
        }
        System.out.println("Add job skill failed");
        return "redirect:/jobskill/"+jobid;
    }
}
