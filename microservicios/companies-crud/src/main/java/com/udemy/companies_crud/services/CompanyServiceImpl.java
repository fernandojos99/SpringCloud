package com.udemy.companies_crud.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.companies_crud.entities.Category;
import com.udemy.companies_crud.entities.Company;
import com.udemy.companies_crud.repositories.CompanyRepository;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service

//Las siguientes 2 estudiar 
@Transactional
@Slf4j

//Esto es para que nos cree un contructor con todos los argumentos (Lombok)
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

	//Dice que el autowired ya no es necesario , solo se ocupa para los test 
	@Autowired
    private final CompanyRepository companyRepository;

    @Override
    public Company create(Company company) {
        company.getWebSites().forEach(webSite -> {
            if (Objects.isNull(webSite.getCategory())) {
                webSite.setCategory(Category.NONE);
            }
        });
        return this.companyRepository.save(company);
   
    }

    @Override
    public Company readByName(String name) {
        return this.companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
    }

    @Override
    public Company update(Company company, String name) {
    	//Primero validar que exista la compañia que vamos a actualizar 
        var companyToUpdate = this.companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
        companyToUpdate.setLogo(company.getLogo());
        companyToUpdate.setFoundationDate(company.getFoundationDate());
        companyToUpdate.setFounder(company.getFounder());
        return this.companyRepository.save(companyToUpdate);
    }

    @Override
    public void delete(String name) {
        var companyToDelete = this.companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
        this.companyRepository.delete(companyToDelete);
    }
}
