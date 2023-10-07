package com.minh.labweek05.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@ToString @Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "job")
public class Job {
    @Id
    private long id;
    @Column
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "com_id")
    private Company company;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "job")
    private List<JobSkill> jobSkills=new ArrayList<JobSkill>();
    @Column
    private String description;
}
