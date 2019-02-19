package com.pgt.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgt.proyecto.dao.PatrocinadorDAO;
import com.pgt.proyecto.dto.ClubDTO;
import com.pgt.proyecto.dto.PatrocinadorDTO;
import com.pgt.proyecto.mapper.ClubMapper;
import com.pgt.proyecto.mapper.PatrocinadorMapper;
import com.pgt.proyecto.model.Club;
import com.pgt.proyecto.model.Patrocinador;

@Service
public class PatrocinadorServiceImpl implements PatrocinadorService{

	@Autowired
	PatrocinadorDAO dao;
	
	@Autowired
	PatrocinadorMapper mapper;
	
	@Autowired
	ClubService clubService;
	
	@Autowired
	ClubMapper mapperClub;
	
	public List<PatrocinadorDTO> findAll(String name){
		return name!= null ? mapper.mapToDTO(dao.findByName(name)) : mapper.mapToDTO(dao.findAll());
	}

	@Override
	public PatrocinadorDTO findById(Integer id){
		return mapper.mapToDTO(dao.findById(id).orElse(null));
	}

	@Override
	public PatrocinadorDTO create(PatrocinadorDTO patrocinadorDTO) {
		return mapper.mapToDTO(dao.save(mapper.mapToModel(patrocinadorDTO)));
	}

	@Override
	public void update(Integer id, PatrocinadorDTO patrocinadorDTO) {
		Patrocinador patrocinador = mapper.mapToModel(patrocinadorDTO);
		patrocinador.setId(id);
		dao.saveAndFlush(patrocinador);
	}

	@Override
	public void assign(Integer idpatrocinador, Integer idclub, PatrocinadorDTO patrocinadorDTO) {
		Patrocinador patrocinador = mapper.mapToModel(patrocinadorDTO);
		patrocinador.setId(idpatrocinador);
		
		ClubDTO clubDTO = clubService.findById(idclub);
		Club clubToAdd = mapperClub.mapToModel(clubDTO);
		clubToAdd.setPatrocinador(patrocinador);
		
		clubToAdd.setId(idclub);
		patrocinador.getClubs().add(clubToAdd);
		dao.saveAndFlush(patrocinador);
		
		clubService.assign(idclub, idpatrocinador, clubDTO);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}	

}
