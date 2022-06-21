package com.example.crm.dataAccess.abstracts;

import com.example.crm.entities.concretes.Company;
import com.example.crm.entities.concretes.EntityStatus;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CompanyDao extends JpaRepository<Company, Integer> {

    Company findByCompanyName(String companyName);
    List<Company> findByEntityStatus(EntityStatus entityStatus);
    List<Company> findByEntityStatusOrderById(EntityStatus entityStatus);

    /**
     * findByID method use EntityManager find() method internally and getById method use EntityManager getReference() method.
     * Therefore, findById returns actual object and getById returns reference of object.
     **/
    Company findById(int id);
    Company findByIdAndEntityStatus(Integer id, EntityStatus entityStatus);
    @Modifying
    @Transactional
    @Query("update Company c set c.companyName = :companyName where c.id=:id")
    void updateCompanyName(int id, String companyName);
    @Modifying
    @Transactional
    @Query("update Company c set c.entityStatus = 0 where c.id =:id")
    void activateCompany(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query("update Company c set c.entityStatus = 1 where c.id =:id")
    void deleteCompany(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update Company c set c.entityStatus = 2 where c.id =:id")
    void disableCompany(@Param("id") Integer id);

}
