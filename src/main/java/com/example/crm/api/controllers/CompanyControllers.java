package com.example.crm.api.controllers;

import com.example.crm.business.abstracts.CompanyService;
import com.example.crm.entities.concretes.Company;
import com.example.crm.entities.concretes.EntityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyControllers {

    private CompanyService companyService;
    @Autowired
    public CompanyControllers(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/findAll")
    public List<Company> getAll() {
        return this.companyService.findAll();
    }
    @GetMapping("/findById/{id}")
    ResponseEntity<Company> getById(@PathVariable("id") int id) {
        return ResponseEntity.status(200).body(this.companyService.findByIdx(id));
    }
    @GetMapping("/findByName")
    ResponseEntity<Company> getByCompanyName(@RequestParam String companyName) {
        return ResponseEntity.status(200).body(this.companyService.findByCompanyName(companyName));
    }

    @PostMapping("/add")
    public void add(@RequestBody Company company) {
        this.companyService.add(company);
    }
    @DeleteMapping("/delete")
    ResponseEntity delete(@RequestParam Integer id) {
        this.companyService.delete(id);
        return ResponseEntity.status(200).body("The company is deleted from db.");
    }
    @PutMapping("/updateCompanyName")
    public void updateCompanyName(@RequestParam String companyName,@RequestParam int id) {
        this.companyService.updateCompanyName(id, companyName);
    }

    @PutMapping("/update")
    public void update(@RequestBody Company company){
        this.companyService.update(company);
    }

    @PatchMapping("/deleteCompany")
    ResponseEntity deleteCompany(@RequestParam Integer id) {
        this.companyService.deleteCompany(id);
        return ResponseEntity.status(200).body("The company is deleted.");
    }
    @PatchMapping("/activateCompany")
    ResponseEntity activateCompany(@RequestParam Integer id) {
        this.companyService.activateCompany(id);
        return ResponseEntity.status(200).body("The company is activated.");
    }
    @PatchMapping("/disableCompany")
    ResponseEntity disableCompany(@RequestParam Integer id) {
        this.companyService.disableCompany(id);
        return ResponseEntity.status(200).body("The company is disable.");
    }

    @GetMapping("/findActiveCompanyById/{id}")
    ResponseEntity findActiveCompanyById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(200).body(this.companyService.findActiveCompanyById(id));
    }

}
