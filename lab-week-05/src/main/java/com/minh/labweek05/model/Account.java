package com.minh.labweek05.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String role;


    public Account(String email, String password, String company) {
        this.email = email;
        this.password = password;
        this.role = company;
    }
}
