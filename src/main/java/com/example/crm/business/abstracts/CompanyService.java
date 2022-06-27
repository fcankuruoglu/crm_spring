package com.example.crm.business.abstracts;

import com.example.crm.dataTransferObjects.CompanyDto;
import com.example.crm.entities.concretes.Company;


import java.util.List;


public interface CompanyService {
    List<Company> findAllActiveCompanies();

    List<CompanyDto> getAll();
    CompanyDto getByName(String companyName);
    CompanyDto getById(int id);

    List<Company> findAll();
    List<Company> findAllActiveSorted();
    List<Company> findAllSorted();
    Company findById(int id); // to filter it use query in dao.
    Company findByCompanyName(String companyName);
    Company findActiveCompanyById(Integer id);

    void add(Company company);
    void delete(int id);
    Company updateCompanyName(int id, String companyName);

    // TODO: check if id is not exists, will it create new data
    // FIXME: if id exists, update company - add result classes for if id does not exists
    void update(Company company); // use db save - if there is a company with given id it only updates but if id is not exists prob it will create new data.


    void deleteCompany(Integer id);
    void activateCompany(Integer id);
    void disableCompany(Integer id);



}
