package com.minh.labweek05.model;

import com.minh.labweek05.enums.SkillLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data@AllArgsConstructor@NoArgsConstructor@ToString
@Entity
@Table(name = "job_skill")
@IdClass(JobSkill.class)
public class JobSkill implements Serializable {
    @Enumerated(EnumType.ORDINAL)
    private SkillLevel skillLevel;
    @Id
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id")
    private Job job;
    @Column
    private String moreInfo;
    @Id
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "skill_id")
    private Skill skill;
}
