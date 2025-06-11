package com.udemy.report_ms.streams;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReportPublisher {

	//Esto ya es un bind dice 
	private final StreamBridge streamBridge;
	
	
    /*
     * Este es el que se encarga de publicar el reporte , que siempre se 
     * deben de crear 3 topicos.
    * topic name -> consumerReport
     */
    public void publishReport(String report) {
       this.streamBridge.send("consumerReport", report);
        this.streamBridge.send("consumerReport-in-0", report);
        this.streamBridge.send("consumerReport-out-0", report);
    }
	
}
