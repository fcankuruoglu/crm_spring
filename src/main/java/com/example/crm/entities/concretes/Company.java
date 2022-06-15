package com.example.crm.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "companies")
@Data   // lombok - getter and setter
@AllArgsConstructor // lombok - constructor with all arguments
@NoArgsConstructor  // lombok - constructor with no arguments
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String companyName;

    @Column(name = "city")
    private String companyCity;

    @Column(name = "address")
    private String companyAddress;

    @Column(name = "domain_name")
    private String companyDomain;

    @Column(name = "telephone_number")
    private String companyPhoneNumber;



}
