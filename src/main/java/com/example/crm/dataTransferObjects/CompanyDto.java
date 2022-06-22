package com.example.crm.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private Integer id;
    private String companyName;
    private String companyCity;
    private Enum status;


}
