package com.minh.labweek05.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Getter
@Setter
@Table(name = "candidate")
public class Candidate implements Serializable {
    @Column(columnDefinition = "varchar(20)")
    private String phone;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "date")
    private LocalDate dob;
    @Column
    private String email;
    @Column
    private String fullName;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private Address address;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<CandidateSkill> candidateSkills=new HashSet<CandidateSkill>();
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Experience> experience=new ArrayList<Experience>();

    public Candidate(String s, LocalDate now, String s1, String s2, Account account, Address address, Set<CandidateSkill> candidateSkills, ArrayList<Experience> experiences) {
        this.phone=s;
        this.dob=now;
        this.email=s1;
        this.fullName=s2;
        this.account=account;
        this.address=address;
        this.candidateSkills=candidateSkills;
        this.experience=experiences;
    }
}
