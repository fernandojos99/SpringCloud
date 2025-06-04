package com.udemy.report_ms.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.netflix.discovery.EurekaClient;
import com.udemy.report_ms.helpers.ReportHelper;
import com.udemy.report_ms.models.Company;
import com.udemy.report_ms.models.WebSite;
import com.udemy.report_ms.repositories.CompaniesFallbackRepository;
import com.udemy.report_ms.repositories.CompaniesRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
//Para que ahi se encargue de hacer las dependencias
@AllArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService{

	private final CompaniesRepository companiesRepository;
	//private final EurekaClient eurekaClient;
	private final ReportHelper reportHelper;
	private final CompaniesFallbackRepository companiesFallbackRepository;
	private final Resilience4JCircuitBreakerFactory  circuitBreakerFactory  ;
	
	
//	@Override
//	public String makeReport(String name) {
//		// TODO Auto-generated method stub
//	    //reportHelper.readTemplate();
//	    //return this.companiesRepository.getByName(name).orElseThrow().getName();
//		return reportHelper.readTemplate(this.companiesRepository.getByName(name).orElseThrow());
//	}


	// Se actualizo el makeReport para implementar el circuitBreaker
    @Override
    public String makeReport(String name) {
   // 	"Ese un nombre nomas"/ 
        var circuitBreaker = this.circuitBreakerFactory.create("companies-circuitbreaker");
        return circuitBreaker.run(
                () -> this.makeReportMain(name),
                //Esto es para que reciba un error 
                throwable -> this.makeReportFallback(name, throwable)
        );
    }
	
	
	
	
//	@Override
//	public String saveReport(String nameReport) {
//		// TODO Auto-generated method stub
//		
//	   var company = Company.builder()
//                .name("test")
//                .foundationDate(LocalDate.now())
//                .founder("test")
//                .webSites(List.of())
//                .build();
//	   this.companiesRepository.postByName(company);
//	return null;
//	}
	@Override
	public String saveReport(String report) {
	       var format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	       var placeholders = this.reportHelper.getPlaceholdersFromTemplate(report);
	        var webSites = Stream.of(placeholders.get(3))
	                .map(website -> WebSite.builder().name(website).build())
	                .toList();

	        var company = Company.builder()
	                .name(placeholders.get(0))
	                .foundationDate(LocalDate.parse(placeholders.get(1), format))
	                .founder(placeholders.get(2))
	                .webSites(webSites)
	                .build();

	        this.companiesRepository.postByName(company);
	        return "Saved";
	    }

	@Override
	public void deleteReport(String name) {
		// TODO Auto-generated method stub
		this.companiesRepository.deleteByName(name);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private String makeReportMain(String name) {
		return reportHelper.readTemplate(this.companiesRepository.getByName(name).orElseThrow());
	}

	private String makeReportFallback(String name,Throwable error ) {
		log.warn(error.getMessage());
		return reportHelper.readTemplate(this.companiesFallbackRepository.getByName(name));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
