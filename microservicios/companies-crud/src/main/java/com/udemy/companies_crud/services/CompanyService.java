package com.udemy.companies_crud.services;

import com.udemy.companies_crud.entities.Company;

public interface CompanyService {

	
	// Esto es el crud 
    Company create(Company company);
    Company readByName(String name);
    Company update(Company company, String name);
    void  delete(String name);
}