package com.minh.labweek05.repositories;

import com.minh.labweek05.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}