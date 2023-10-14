package com.minh.labweek05.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data@NoArgsConstructor@AllArgsConstructor@ToString
@Entity
@Table(name = "company")
public class Company implements Serializable {
    @Id
    private long id;
    @Column
    private String name;
    @Column
    private String about;
    @ManyToOne
    private Address address;
    @Column
    private String phone;
    @Column
    private String webURL;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "company")
    private List<Job> jobs=new ArrayList<Job>();
    @Column
    private String email;
}
