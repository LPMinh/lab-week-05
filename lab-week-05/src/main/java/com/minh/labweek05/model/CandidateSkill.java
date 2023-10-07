package com.minh.labweek05.model;

import com.minh.labweek05.enums.SkillLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data@ToString@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "candidate_skill")
public class CandidateSkill {
    private SkillLevel skillLevel;
    @Id
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Skill skill;
    @Id
    @ManyToOne
    @JoinColumn(name="skill_id")
    private Candidate candidate;
    @Column
    private String moreInfo;
}
