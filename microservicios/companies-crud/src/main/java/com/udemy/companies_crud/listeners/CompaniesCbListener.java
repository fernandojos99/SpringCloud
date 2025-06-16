package com.udemy.companies_crud.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.companies_crud.entities.Company;
import com.udemy.companies_crud.services.CompanyService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class CompaniesCbListener {

    private final CompanyService companyService;
    private final ObjectMapper objectMapper;

    // Para que este metodo este al pendiente escuchando los mensajes
    @KafkaListener(topics = "consumerCbReport", groupId = "circuitbreaker")
    public void insertMsgEvent(String companyEvent) throws JsonProcessingException {
        log.info("Received event circuitbreaker {}", companyEvent);
        
        //transformando el evento en string en un company.class
        var company = this.objectMapper.readValue(companyEvent, Company.class);

        this.companyService.create(company);
    }
}