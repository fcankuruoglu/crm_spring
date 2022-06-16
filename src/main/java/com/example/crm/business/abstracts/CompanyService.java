package com.example.crm.business.abstracts;

import com.example.crm.entities.concretes.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAll(boolean isDeleted);
    // TODO: add getById and in controller get mapping should be /id
    void add(Company company);
    void delete(int id);
    void updateCompanyName(int id, String companyName);

    // TODO: check if id is not exists, will it create new data
    // FIXME: if id exists, update company - add result classes for if id does not exists
    void update(Company company); // use db save - if there is a company with given id it only updates but if id is not exists prob it will create new data.
    Company getByCompanyName(String companyName);
}
