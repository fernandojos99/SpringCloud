package com.udemy.report_ms.helpers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.udemy.report_ms.models.Company;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ReportHelper {

	//Para inyectar como string se ocupa con application.properties o por configserver
    @Value("${report.template}")
    private String reportTemplate;

    public String readTemplate(Company company) {
    //public String readTemplate() {
        return this.reportTemplate
                .replace("{company}", company.getName())
                .replace("{foundation_date}", company.getFoundationDate().toString())
                .replace("{founder}", company.getFounder())
                .replace("{web_sites}", company.getWebSites().toString());
    }


    //Recibe un string y eso lo divide con el fin de pasarlo y despues convertirse en Company
    public List<String> getPlaceholdersFromTemplate(String template) {
        var split = template.split("\\{");

        return Arrays.stream(split)
                .filter(line -> !line.isEmpty())
                .map(line -> {
                    var index = line.indexOf("}");
                    return line.substring(0, index);
                })
                .collect(Collectors.toList());
    }
}