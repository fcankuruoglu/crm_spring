package com.example.crm.api.controllers;

import com.example.crm.business.abstracts.CompanyService;
import com.example.crm.entities.concretes.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<Company> getAll() {
        return this.companyService.getAll();
    }

    @PostMapping("add")
    public void add(@RequestBody Company company) {
        this.companyService.add(company);

    }
    @PutMapping("update")
    public void update(@RequestBody Company company, int id) {
        this.companyService.update(company, id);
    }

    @GetMapping("getByName")
    public Company getByCompanyName(@RequestParam String companyName) {
        return this.companyService.getByCompanyName(companyName);
    }
}
