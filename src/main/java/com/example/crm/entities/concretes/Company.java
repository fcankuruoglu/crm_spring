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
//@SQLDelete(sql = "UPDATE companies SET deleted=true WHERE id=?") // Overwriting sql delete command for soft-deleting. It only set deleted column true.
// @Where(clause = "deleted=false") // only get company that are not softly deleted. This way we cannot access deleted=true objects
@FilterDef(name ="deletedCompanyFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean")) // this annotation defines the basic requirements that will be used by @Filter
@Filter(name = "deletedCompanyFilter", condition = "deleted = :isDeleted" ) // setting filter condition and filter requirement.
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

    @Column(name= "status")
    private EntityStatus entityStatus = EntityStatus.ACTIVE;




}
