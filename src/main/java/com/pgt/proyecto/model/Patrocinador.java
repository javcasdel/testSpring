package com.pgt.proyecto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Patrocinador {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patrocinador")
	private List<Club> clubs = new ArrayList<>();
	
	private String name;

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

	public List<Club> getClubs() {
		return clubs;
	}

	public void setClub(List<Club> clubs) {
		this.clubs = clubs;
	}
}
