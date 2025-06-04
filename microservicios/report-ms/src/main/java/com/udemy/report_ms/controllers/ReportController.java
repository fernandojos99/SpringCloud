package com.udemy.report_ms.controllers;



import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.udemy.report_ms.services.ReportService;

import java.util.Map;

@RestController
//Para poner como voy a acceder a este controllador 
@RequestMapping(path = "report")
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;

    @GetMapping(path = "{name}")
    //Map se ocupa para crear un mapa 
    public ResponseEntity<Map<String, String>> getReport(@PathVariable String name) {
    	//un mapa mediante clave valor 
        var response = Map.of("report", this.reportService.makeReport(name));
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<String> postReport(@RequestBody String report) {
       return ResponseEntity.ok(this.reportService.saveReport(report));
//    	var response=this.reportService.saveReport(report);
//    	return ResponseEntity.ok("OK");
    }

    @DeleteMapping(path = "{name}")
    public ResponseEntity<Void> deleteReport(@PathVariable String name) {
        this.reportService.deleteReport(name);
        return ResponseEntity.noContent().build();
    }
}