package com.udemy.companies_crud.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
//@Table(name="web_site")
// Solo escribi Data y el lombok se importo solo 
@Data 

public class WebSite implements Serializable {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column (columnDefinition="category")
	@Enumerated(value=EnumType.STRING)
	private Category category;
	private String description;
	
}

