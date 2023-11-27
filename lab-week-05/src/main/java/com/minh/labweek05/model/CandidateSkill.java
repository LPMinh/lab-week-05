package com.minh.labweek05.model;

import com.minh.labweek05.enums.SkillLevel;
import com.minh.labweek05.pk.CandidateSkillPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data@ToString@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "candidates_skill")
@IdClass(CandidateSkillPK.class)
public class CandidateSkill implements Serializable {

    @Column(name = "skill_level")
    @Enumerated(EnumType.ORDINAL)
    private SkillLevel skillLevel;
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "skill_id")
    private Skill skill;
    @Id
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="can_id",referencedColumnName = "id")
    private Candidate candidate;
    @Column
    private String moreInfo;
}
