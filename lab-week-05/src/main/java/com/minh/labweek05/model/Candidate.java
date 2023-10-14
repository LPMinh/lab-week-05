package com.minh.labweek05.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "candidate")
public class Candidate implements Serializable {
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address")
    private Address address;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CandidateSkill> candidateSkills=new ArrayList<CandidateSkill>();
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Experience> experience=new ArrayList<Experience>();
}
