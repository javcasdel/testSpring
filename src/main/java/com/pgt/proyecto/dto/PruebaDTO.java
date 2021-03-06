package com.pgt.proyecto.dto;

public class PruebaDTO {
	private Integer id;
	
	private String name;
	
	private String date;
	
	public PruebaDTO() {}
	
	public PruebaDTO(Integer id,String name,String date) {
		this.id = id;
		this.name = name;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}	
}
