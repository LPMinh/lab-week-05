package com.minh.labweek05.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data@NoArgsConstructor@AllArgsConstructor@ToString
@Table(name = "experience")
@Getter
@Setter
@Entity
public class Experience implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Experience(LocalDate now, Candidate candidate, LocalDate now1, String name, String position) {
        this.toDate=now;
        this.candidate=candidate;
        this.fromDate=now1;
        this.companyName=name;
        this.role=position;
    }
}
