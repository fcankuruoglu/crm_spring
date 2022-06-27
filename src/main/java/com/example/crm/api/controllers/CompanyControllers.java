package com.example.crm.api.controllers;

import com.example.crm.business.abstracts.CompanyService;
import com.example.crm.dataTransferObjects.CompanyDto;
import com.example.crm.entities.concretes.Company;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/company")
public class CompanyControllers {

    private final CompanyService companyService;
    @Autowired
    public CompanyControllers(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/getAll")
    ResponseEntity<List<CompanyDto>> getAll(){
        return ResponseEntity.status(200).body(this.companyService.getAll());
    }
    @GetMapping("/getByName")
    ResponseEntity<CompanyDto> getByName(String companyName) {
        return ResponseEntity.status(200).body(this.companyService.getByName(companyName));
    }
    @GetMapping("/getById")
    ResponseEntity<CompanyDto> getById(int id) {
        return ResponseEntity.status(200).body(this.companyService.getById(id));
    }



    @GetMapping("/findAll")
    public List<Company> findAll() {
        return this.companyService.findAll();
    }
    @GetMapping("/findAllActiveCompanies")
    public List<Company> findAllActiveCompanies() {
        return this.companyService.findAllActiveCompanies();
    }
    @GetMapping("/findAllSortedActiveCompanies")
    public List<Company> findAllActiveSorted() {
        return this.companyService.findAllActiveSorted();
    }
    @GetMapping("/findAllSorted")
    public List<Company> findAllSorted() {
        return this.companyService.findAllSorted();
    }
    @GetMapping("/findById/{id}")
    ResponseEntity<Company> findById(@PathVariable("id") int id) {
        return ResponseEntity.status(200).body(this.companyService.findById(id));
    }
    @GetMapping("/findActiveCompanyById/{id}")
    ResponseEntity findActiveCompanyById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(200).body(this.companyService.findActiveCompanyById(id));
    }
    @GetMapping("/findByName")
    ResponseEntity<Company> getByCompanyName(@RequestParam String companyName) {
        return ResponseEntity.status(200).body(this.companyService.findByCompanyName(companyName));
    }

    @PostMapping("/add")
    ResponseEntity add(@RequestBody Company company) {
        this.companyService.add(company);
        return ResponseEntity.status(200).body("The company is added to db.");
    }
    @PutMapping("/updateCompanyName")
    ResponseEntity updateCompanyName(@RequestParam String companyName,@RequestParam Integer id) {
        return ResponseEntity.status(200).body(this.companyService.updateCompanyName(id, companyName));
    }
    @PutMapping("/update")
    public void update(@RequestBody Company company){
        this.companyService.update(company);
    }
    @DeleteMapping("/delete")
    ResponseEntity delete(@RequestParam Integer id) {
        this.companyService.delete(id);
        return ResponseEntity.status(200).body("The company is deleted from db.");
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



}
