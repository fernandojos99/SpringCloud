package com.udemy.companies_crud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.companies_crud.entities.Company;

//El tipo del id es long
public interface CompanyRepository extends JpaRepository<Company, Long> {

	// Para hacer metodos personalizados en JPA
    Optional<Company> findByName(String name);
}