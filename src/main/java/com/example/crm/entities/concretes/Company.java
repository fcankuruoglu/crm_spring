package com.example.crm.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data   // lombok - getter and setter
@AllArgsConstructor // lombok - constructor with all arguments
@NoArgsConstructor  // lombok - constructor with no arguments
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String companyName;

    @Column(name = "city")
    private String companyCity;

    @Column(name = "address")
    private String companyAddress;

    @Column(name = "domain_name")
    private String companyDomain;

    @Column(name = "phone_number")
    private String companyPhoneNumber;

    @Enumerated(EnumType.ORDINAL)
    @Column(name= "status")
    private EntityStatus entityStatus = EntityStatus.ACTIVE;




}
