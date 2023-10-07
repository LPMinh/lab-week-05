package com.minh.labweek05.repositories;

import com.minh.labweek05.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}