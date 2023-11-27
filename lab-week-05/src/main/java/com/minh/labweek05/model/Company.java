package com.minh.labweek05.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data@NoArgsConstructor@AllArgsConstructor@ToString
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String about;
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private Address address;
    @Column
    private String phone;
    @Column
    private String webURL;
    @Column
    private String email;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "company")
    private List<Job> jobs=new ArrayList<Job>();

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    public Company(String name, String profession, Address address, String s, String url, String s1, ArrayList<Job> jobs, Account account) {
        this.name=name;
        this.about=profession;
        this.address=address;
        this.phone=s;
        this.webURL=url;
        this.email=s1;
        this.jobs=jobs;
        this.account=account;
    }
}
