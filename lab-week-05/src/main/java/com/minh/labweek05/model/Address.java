package com.minh.labweek05.model;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Indexed;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
@Table(name = "address")
public class Address  implements Serializable {

    @Column(columnDefinition = "varchar(50)")
    private String city;
    @Column(columnDefinition = "smallint(6)")
    private int country;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "zipcode",columnDefinition = "varchar(20)")
    private String zipCode;
    @Column(columnDefinition = "varchar(150)")
    private String street;


    public Address(String city, int i, String s, String s1) {
        this.city=city;
        this.country=i;
        this.zipCode=s;
        this.street=s1;
    }
}
