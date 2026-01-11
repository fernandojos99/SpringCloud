package com.udemy.report_ms.services;

public interface ReportService {
	
	//Literal esto es para hacer , salvar y borrar reportes
	
	String makeReport(String name);
	String saveReport(String nameReport);
	void deleteReport(String name );

}
