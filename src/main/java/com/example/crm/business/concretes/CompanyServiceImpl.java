package com.example.crm.business.concretes;

import com.example.crm.business.abstracts.CompanyService;
import com.example.crm.dataAccess.abstracts.CompanyDao;
import com.example.crm.dataTransferObjects.CompanyDto;
import com.example.crm.entities.concretes.Company;
import com.example.crm.entities.concretes.EntityStatus;
import com.example.crm.exception.custom.AlreadyExistsException;
import com.example.crm.exception.custom.CompanyNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyDao companyDao;
    private final ModelMapper mapper;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao, ModelMapper mapper) {
        this.companyDao = companyDao;
        this.mapper = mapper;
    }

    @Override
    public List<CompanyDto> getAll(){
        return this.companyDao.findByEntityStatusOrderById(EntityStatus.ACTIVE)
                .stream()
                .map(companyEntity -> mapper.map(companyEntity, CompanyDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public CompanyDto getByName(String companyName) {
        Company company =  this.companyDao.findByCompanyName(companyName);
        if(ObjectUtils.isEmpty(company)) {throw new CompanyNotFoundException(companyName);}
        return mapper.map(company, CompanyDto.class);
    }
    @Override
    public CompanyDto getById(int id){
        Company company =this.companyDao.findById(id);
        if(ObjectUtils.isEmpty(company)) {throw new CompanyNotFoundException(id);}
        return mapper.map(company, CompanyDto.class);
    }

    @Override
    public List<Company> findAll() {
        return new ArrayList<>(this.companyDao.findAll());
    }
    @Override
    public List<Company> findAllActiveCompanies() {
        return this.companyDao.findByEntityStatus(EntityStatus.ACTIVE);
    }
    @Override
    public List<Company> findAllActiveSorted() {
        return this.companyDao.findByEntityStatusOrderById(EntityStatus.ACTIVE);
    }
    public List<Company> findAllSorted(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return this.companyDao.findAll(sort);
    }

    @Override
    public Company findById(int id) {
        Company temp = this.companyDao.findById(id);
        if(ObjectUtils.isEmpty(temp)) {throw new CompanyNotFoundException(id);}
        return temp;
    }
    @Override
    public Company findActiveCompanyById(Integer id) {
        Company temp = this.companyDao.findByIdAndEntityStatus(id, EntityStatus.ACTIVE);
        if(ObjectUtils.isEmpty(temp)) {throw new CompanyNotFoundException(id);}
        return temp;
    }
    @Override
    public Company findByCompanyName(String companyName) {
        Company temp = this.companyDao.findByCompanyName(companyName);
        if(ObjectUtils.isEmpty(temp)) {throw new CompanyNotFoundException(companyName);}
        return temp;
    }

    @Override
    public void add(Company company) {
        Integer companyId = company.getId();
        if(this.companyDao.findById(companyId).isPresent()) {
            throw new AlreadyExistsException(companyId);
        }
        this.companyDao.save(company);
    }
    // db delete operation. - it is hard-delete
    @Override
    public void delete(int id) {
        this.companyDao.deleteById(id);
    }
    // partial updating operation - it only updates company name of an existed company
    @Override
    public Company updateCompanyName(int id,String companyName) {
        Company temp = this.companyDao.findById(id);
        if (ObjectUtils.isEmpty(temp)) {throw new CompanyNotFoundException(id);}
        this.companyDao.updateCompanyName(id, companyName);
        return temp;
    }
    // TODO : check if is solid solution or it can be improved.
    @Override
    public void update(Company company) {
        Integer companyId = company.getId();
        Company temp = this.companyDao.findById(companyId).get();
        if(ObjectUtils.isEmpty(temp)) {throw new CompanyNotFoundException(companyId);}
        temp.setCompanyName(company.getCompanyName());
        temp.setCompanyCity(company.getCompanyCity());
        temp.setCompanyAddress(company.getCompanyAddress());
        temp.setCompanyDomain(company.getCompanyDomain());
        temp.setCompanyPhoneNumber(company.getCompanyPhoneNumber());
        this.companyDao.save(temp);
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


}
