package com.minh.labweek05.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
@ToString  @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "job")
@Getter @Setter @Data
public class Job implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "com_id")
    private Company company;
    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "job",orphanRemoval = true)
    private List<JobSkill> jobSkills=new ArrayList<JobSkill>();
    @Column
    private String description;

    public Job(String title, String name, ArrayList<JobSkill> jobSkills) {
        this.name=title;
        this.description=name;
        this.jobSkills=jobSkills;
    }

    public Job(long id, String name, Company company, String description) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.description = description;
    }
    public void addJobSkill(JobSkill jobSkill) {
        jobSkills.add(jobSkill);
        jobSkill.setJob(this);
    }

    public Job(Company company, String title, String position, ArrayList<JobSkill> jobSkills) {
        this.company=company;
        this.name=title;
        this.description=position;
        this.jobSkills=jobSkills;
    }
}
