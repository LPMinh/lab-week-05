package com.minh.labweek05.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data@NoArgsConstructor@AllArgsConstructor@ToString
@Table(name = "experience")
@Entity
public class Experience {
    @Id
    private long id;
    @Column(columnDefinition = "date")
    private LocalDate toDate;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Candidate candidate;
    @Column(columnDefinition = "date")
    private LocalDate fromDate;
    @Column
    private String companyName;
    @Column
    private String role;
    @Column
    private String workDescription;
}
