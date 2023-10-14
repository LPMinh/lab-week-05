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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
@Service
@RestController
@RequestMapping(name = "/api/candidate")
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @PostMapping("")
    ResponseEntity<Candidate> insertCandidate(@RequestBody Candidate candidate){
        if(!candidateRepository.save(candidate).equals(null)){
            return ResponseEntity.ok().body(candidate);
        }else{
            return ResponseEntity.badRequest().body(candidate);
        }
    }
    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy,
                                   String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);//findFirst.../find
    }
}
