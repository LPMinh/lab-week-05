package com.minh.labweek05.model;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "address")
public class Address  implements Serializable {
    @Column(columnDefinition = "varchar(50)")
    private String city;
    @Column(columnDefinition = "smallint(6)")
    private CountryCode country;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "zipcode",columnDefinition = "varchar(20)")
    private String zipCode;
    @Column(columnDefinition = "varchar(150)")
    private String street;

}
