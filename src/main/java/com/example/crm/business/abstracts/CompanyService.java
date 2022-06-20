package com.example.crm.business.abstracts;

import com.example.crm.entities.concretes.Company;
import com.example.crm.entities.concretes.EntityStatus;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> findAll();
    List<Company> findAllx(boolean isDeleted);
    Company findByIdx(int id); // to filter it use query in dao.
    Company findByCompanyName(String companyName);
    void add(Company company);
    void delete(int id);
    void updateCompanyName(int id, String companyName);

    // TODO: check if id is not exists, will it create new data
    // FIXME: if id exists, update company - add result classes for if id does not exists
    void update(Company company); // use db save - if there is a company with given id it only updates but if id is not exists prob it will create new data.


    void deleteCompany(Integer id);
    void activateCompany(Integer id);
    void disableCompany(Integer id);
    Company findActiveCompanyById(Integer id);
}
