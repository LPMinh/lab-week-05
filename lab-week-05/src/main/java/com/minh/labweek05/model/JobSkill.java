package com.minh.labweek05.model;

import com.minh.labweek05.enums.SkillLevel;
import com.minh.labweek05.pk.JobSkillPK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data@AllArgsConstructor@NoArgsConstructor@ToString
@Entity
@Table(name = "job_skill")
@IdClass(JobSkillPK.class)
@Getter
@Setter
public class JobSkill implements Serializable {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "skill_level")
    private SkillLevel skillLevel;
    @Id
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "job_id")
    private Job job;
    @Column
    private String moreInfo;
    @Id
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    @JoinColumn(name = "skill_id")
    private Skill skill;


    public JobSkill(SkillLevel skillLevel, Skill skill1, Job job) {
        this.skillLevel=skillLevel;
        this.skill=skill1;
        this.job=job;
    }

    public JobSkill(SkillLevel skillLevel, Skill skill) {
        this.skillLevel=skillLevel;
        this.skill=skill;
    }

}
