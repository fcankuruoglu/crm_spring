package com.example.crm.dataAccess.abstracts;

import com.example.crm.entities.concretes.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CompanyDao extends JpaRepository<Company, Integer> {

    @Modifying
    @Transactional
    @Query("update Company c set c.companyName = :#{#company.companyName}, c.companyAddress = :#{#company.companyAddress} where c.id=:id")
    void update(@Param("company") Company company, int id);
    Company getByCompanyName(String companyName);

}
