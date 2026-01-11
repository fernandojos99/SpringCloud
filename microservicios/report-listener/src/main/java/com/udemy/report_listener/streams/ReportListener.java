package com.udemy.report_listener.streams;


import lombok.AllArgsConstructor;
//import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udemy.report_listener.documents.ReportDocument;
import com.udemy.report_listener.repositories.ReportRepository;

import java.util.function.Consumer;

@Configuration
@Slf4j
@AllArgsConstructor
public class ReportListener {

    private final ReportRepository reportRepository;

    @Bean
    public Consumer<String> consumerReport() {
        return report -> {
            this.reportRepository.save(ReportDocument.builder().content(report).build());
            log.info("Saving report: {}", report);
//        	log.info("🚀 Aplicación iniciada correctamentee");
//        	log.info("Consumiendo report{}",report);
        };
    }
}