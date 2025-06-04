package com.udemy.report_ms.repositories;

import java.util.Optional;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.udemy.report_ms.beans.LoadBalancerConfiguration;
import com.udemy.report_ms.models.Company;

//Nombre del microservicio(al que nos conectaremos ) con el cual esta registrado en eureka 
@FeignClient(name="companies-crud")
@LoadBalancerClient(name="companies-crud",configuration=LoadBalancerConfiguration.class)

public interface CompaniesRepository {

	
// Aqui hacer tal cual como lo hacemos para spring web
	//La direccion de donde vamos a tomar la información (el microservicio)
    @GetMapping(path = "/companies-crud/company/{name}")
    Optional<Company> getByName(@PathVariable String name);

    @PostMapping(path = "/companies-crud/company")
    Optional<Company> postByName(@RequestBody Company company);

    @DeleteMapping(path = "/companies-crud/company/{name}")
    void deleteByName(@PathVariable String name);
}