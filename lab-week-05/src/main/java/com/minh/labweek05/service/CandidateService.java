package com.minh.labweek05.service;

import com.minh.labweek05.model.Candidate;
import com.minh.labweek05.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping(name = "/api/candidate")
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @PostMapping("")
    ResponseEntity<Candidate> insertCandidate(@RequestBody Candidate candidate){
        if(candidateRepository.save(candidate)){
            return ResponseEntity.ok().body(candidate);
        }
    }

}
