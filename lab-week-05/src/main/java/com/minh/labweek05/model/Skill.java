package com.minh.labweek05.model;

import com.minh.labweek05.enums.SkillType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
public class Skill implements Serializable {
    @Enumerated(EnumType.ORDINAL)
    private SkillType type;
    @Column
    private String skillName;
    @Column
    private String skillDescription;
    @Id
    private long id;
    @OneToMany(mappedBy = "skill")
    private List<JobSkill> jobSkills=new ArrayList<JobSkill>();
}
