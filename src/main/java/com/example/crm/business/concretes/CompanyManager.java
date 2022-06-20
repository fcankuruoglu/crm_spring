package com.example.crm.business.concretes;

import com.example.crm.business.abstracts.CompanyService;
import com.example.crm.dataAccess.abstracts.CompanyDao;
import com.example.crm.entities.concretes.Company;
import com.example.crm.entities.concretes.EntityStatus;
import com.example.crm.exception.custom.CompanyNotFoundException;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyManager implements CompanyService {
    private CompanyDao companyDao;
    private EntityManager entityManager;

    @Autowired
    public CompanyManager(CompanyDao companyDao, EntityManager entityManager) { this.companyDao = companyDao;
        this.entityManager = entityManager;
    }
    @Override
    public List<Company> findAllx(boolean isDeleted) {
        Session session = this.entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedCompanyFilter");
        filter.setParameter("isDeleted", isDeleted);
        ArrayList<Company> companiesWithFilter = new ArrayList<>(this.companyDao.findAll());
        session.disableFilter("deletedCompanyFilter");
        return companiesWithFilter;
    }

    @Override
    public List<Company> findAll() {
        return this.companyDao.findByEntityStatus(EntityStatus.ACTIVE);
    }

    @Override
    public Company findByIdx(int id) {
        Company temp = this.companyDao.findById(id);
        if(ObjectUtils.isEmpty(temp)) {throw new CompanyNotFoundException(id);}
        return temp;
    }

    // FIXME: try-catch not working
    @Override
    public Company findByCompanyName(String companyName) {
        Company temp = this.companyDao.findByCompanyName(companyName);
        if(ObjectUtils.isEmpty(temp)) {throw new CompanyNotFoundException(companyName);}
        return temp;
    }

    @Override
    public void add(Company company) {
        this.companyDao.save(company);
    }

    // db delete operation. - it is hard-delete
    @Override
    public void delete(int id) {
        this.companyDao.deleteById(id);
    }

    // partial updating operation - it only updates company name of an existed company
    @Override
    public void updateCompanyName(int id,String companyName) {
        Company temp = this.companyDao.findById(id);
        if (ObjectUtils.isEmpty(temp)) {throw new CompanyNotFoundException(id);}
        this.companyDao.updateCompanyName(id, companyName);
    }

    // TODO : check if is solid solution or it can be improved.
    @Override
    public void update(Company company) {
        Integer companyId = company.getId();
        if(this.companyDao.findById(companyId).isEmpty()) {throw new CompanyNotFoundException(companyId);}
        Company updatedCompany = this.companyDao.findById(companyId).get();
        updatedCompany.setCompanyName(company.getCompanyName());
        updatedCompany.setCompanyCity(company.getCompanyCity());
        updatedCompany.setCompanyAddress(company.getCompanyAddress());
        updatedCompany.setCompanyDomain(company.getCompanyDomain());
        updatedCompany.setCompanyPhoneNumber(company.getCompanyPhoneNumber());
        this.companyDao.save(updatedCompany);
    }

    @Override
    public void deleteCompany(Integer id) {
        this.companyDao.deleteCompany(id);

    }

    @Override
    public void activateCompany(Integer id) {
        this.companyDao.activateCompany(id);
    }

    @Override
    public void disableCompany(Integer id) {
        this.companyDao.disableCompany(id);
    }
    public Company findActiveCompanyById(Integer id) {
        Company temp = this.companyDao.findByIdAndEntityStatus(id, EntityStatus.ACTIVE);
        if(ObjectUtils.isEmpty(temp)) {throw new CompanyNotFoundException(id);}
        return temp;
    }

}
