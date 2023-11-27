package com.minh.labweek05.model;

import com.minh.labweek05.enums.SkillType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Getter
@Setter
@Entity
public class Skill implements Serializable {

    @Enumerated(EnumType.ORDINAL)
    private SkillType type;
    @Column
    private String skillName;
    @Column
    private String skillDescription;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "skill",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<JobSkill> jobSkills=new ArrayList<JobSkill>();

    public Skill(SkillType type, String skillName, String skillDescription, long id) {
        this.type = type;
        this.skillName = skillName;
        this.skillDescription = skillDescription;
        this.id = id;
    }

    public Skill(SkillType skillType, String seniority, String s) {
        this.type=skillType;
        this.skillName=seniority;
        this.skillDescription=s;
    }

    public Skill(SkillType type, String title, String skillDescription, ArrayList<JobSkill> jobSkills) {
        this.type=type;
        this.skillName=title;
        this.skillDescription=skillDescription;
        this.jobSkills=jobSkills;
    }
}
