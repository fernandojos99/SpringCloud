package com.udemy.report_ms.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;



//@Table(name="web_site")
// Solo escribi Data y el lombok se importo solo 
@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class WebSite implements Serializable {


//	private Long id;
	private String name;

//	private Category category;
//	private String description;
	
}

