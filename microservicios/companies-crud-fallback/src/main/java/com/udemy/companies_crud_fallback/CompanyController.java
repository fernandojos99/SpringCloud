package com.udemy.companies_crud_fallback;

import java.time.LocalDate;
import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(path = "company")
@Slf4j
public class CompanyController {

	
	private static final Company DEFAULT_COMPANY = Company
	        .builder()
	            .id(0L)
	            .founder("Fallback")
	            .name("Fallback company")
	            .logo("http://default-logo.com")
	            .foundationDate(LocalDate.now())
	            .webSites(Collections.emptyList())
	        .build();
	
	
	// Los endpoints tiene que tener la misma identificación que en el microservicio original 
    @GetMapping(path = "{name}")
    public ResponseEntity<Company> get(@PathVariable String name) {
        log.info("GET in fallback: company {}", name);
        return ResponseEntity.ok(DEFAULT_COMPANY);
    }
}
