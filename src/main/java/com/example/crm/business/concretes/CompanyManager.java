package com.example.crm.business.concretes;

import com.example.crm.business.abstracts.CompanyService;
import com.example.crm.dataAccess.abstracts.CompanyDao;
import com.example.crm.entities.concretes.Company;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyManager implements CompanyService {
    private CompanyDao companyDao;
    private EntityManager entityManager;

    @Autowired
    public CompanyManager(CompanyDao companyDao, EntityManager entityManager) { this.companyDao = companyDao;
        this.entityManager = entityManager;
    }
    @Override
    public List<Company> getAll(boolean isDeleted) {
        Session session = this.entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedCompanyFilter");
        filter.setParameter("isDeleted", isDeleted);
        ArrayList<Company> companiesWithFilter = new ArrayList<>(this.companyDao.findAll());
        session.disableFilter("deletedCompanyFilter");
        return companiesWithFilter;
    }
    @Override
    public void add(Company company) {
        this.companyDao.save(company);
    }

    // TODO: Add success or error result
    @Override
    public void delete(int id) {
        this.companyDao.deleteById(id);
    }


    // partial updating operation - it only updates company name of an existed company
    @Override
    public void updateCompanyName(int id,String companyName) {
        this.companyDao.updateCompanyName(id, companyName);
    }

    // TODO: first check that id exists or not, if it exists then update company and return success result. However, if id does not exists return error result.
    // TODO : check if is solid solution or it can be improved.
    @Override
    public void update(Company company) {
        Integer companyId = company.getId();
        Company companyTemp = companyDao.findById(companyId).get();
        companyTemp.setCompanyName(company.getCompanyName());
        companyTemp.setCompanyCity(company.getCompanyCity());
        companyTemp.setCompanyAddress(company.getCompanyAddress());
        companyTemp.setCompanyDomain(company.getCompanyDomain());
        companyTemp.setCompanyPhoneNumber(company.getCompanyPhoneNumber());
        this.companyDao.save(companyTemp);
    }

    @Override
    public Company getByCompanyName(String companyName) {
        return this.companyDao.getByCompanyName(companyName);
    }


}
