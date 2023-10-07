package com.minh.labweek05.repositories;

import com.minh.labweek05.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}