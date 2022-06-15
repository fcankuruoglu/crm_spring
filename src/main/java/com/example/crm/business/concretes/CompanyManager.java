package com.example.crm.business.concretes;

import com.example.crm.business.abstracts.CompanyService;
import com.example.crm.dataAccess.abstracts.CompanyDao;
import com.example.crm.entities.concretes.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyManager implements CompanyService {
    private CompanyDao companyDao;

    @Autowired
    public CompanyManager(CompanyDao companyDao) { this.companyDao = companyDao; }

    @Override
    public List<Company> getAll() {
        return new ArrayList<Company>(this.companyDao.findAll());
    }

    @Override
    public void add(Company company) {
        this.companyDao.save(company);

    }

    @Override
    public void update(Company company, int id) {
        this.companyDao.update(company, id);
    }

    @Override
    public Company getByCompanyName(String companyName) {
        return this.companyDao.getByCompanyName(companyName);
    }


}
