package com.pgt.proyecto.service;

import java.util.List;

import com.pgt.proyecto.dto.PatrocinadorDTO;

public interface PatrocinadorService {

	List<PatrocinadorDTO> findAll(String name);

	PatrocinadorDTO findById(Integer id);

	PatrocinadorDTO create(PatrocinadorDTO clubDTO);

	void update(Integer id, PatrocinadorDTO clubDTO);

	void delete(Integer id);

	void assign(Integer idpatrocinador, Integer idclub, PatrocinadorDTO patrocinadorDTO);

}
