package com.minh.labweek05.repositories;

import com.minh.labweek05.enums.SkillLevel;
import com.minh.labweek05.enums.SkillType;
import com.minh.labweek05.model.Address;
import com.minh.labweek05.model.Job;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
//    @Enumerated(EnumType.ORDINAL)
//    private SkillType type;
//    @Column
//    private String skillName;
//    @Column
//    private String skillDescription;
    @Query("select j from Job j where j.company.address.street=?1 or j.company.address.city=?2")
    public List<Job> findJobNearMe(String street, String city, int country, String zipcode);

    @Query("select j from Job j inner join JobSkill js on j.id=js.job.id where js.skill.skillName like  CONCAT('%',?1,'%') and js.skill.skillDescription like  concat('%',?2,'%') and js.skill.type=?3")
    List<Job> findJobBySkill(String skillName, String skillDescription, SkillType type);

    @Query("select j from Job j inner join JobSkill js on j.id=js.job.id where js.skill.skillName like  CONCAT('%',:query,'%') or js.skill.skillDescription like  concat('%',:query,'%') and js.skillLevel= :level")
    List<Job> findJobBySkillAndLevel(String query, SkillLevel level);

}