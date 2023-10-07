package com.minh.labweek05.repositories;

import com.minh.labweek05.model.Job;
import com.minh.labweek05.model.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSkillRepository extends JpaRepository<JobSkill, Job> {
}