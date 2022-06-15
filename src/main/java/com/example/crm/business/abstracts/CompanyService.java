package com.example.crm.business.abstracts;

import com.example.crm.entities.concretes.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAll();

    void add(Company company);
    void update(Company company, int id);
    Company getByCompanyName(String companyName);
}
