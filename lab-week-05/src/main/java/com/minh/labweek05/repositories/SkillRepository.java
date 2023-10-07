package com.minh.labweek05.repositories;

import com.minh.labweek05.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}