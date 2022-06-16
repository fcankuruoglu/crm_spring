package com.example.crm.api.controllers;

import com.example.crm.business.abstracts.CompanyService;
import com.example.crm.entities.concretes.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/")
public class CompanyControllers {

    private CompanyService companyService;
    @Autowired
    public CompanyControllers(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("getAll")
    public List<Company> getAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {
        return this.companyService.getAll(isDeleted);
    }

    @PostMapping("add")
    public void add(@RequestBody Company company) {
        this.companyService.add(company);
    }
    @DeleteMapping("delete")
    public void delete(@RequestParam int id) {
        this.companyService.delete(id);
    }
    @PutMapping("updateCompanyName")
    public void updateCompanyName(@RequestParam String companyName,@RequestParam int id) {
        this.companyService.updateCompanyName(id, companyName);
    }

    @PutMapping("update")
    public void update(@RequestBody Company company){
        this.companyService.update(company);
    }

    @GetMapping("getByName")
    public Company getByCompanyName(@RequestParam String companyName) {
        return this.companyService.getByCompanyName(companyName);
    }
}
