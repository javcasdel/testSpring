package com.pgt.proyecto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.pgt.proyecto.dto.PatrocinadorDTO;
import com.pgt.proyecto.model.Patrocinador;

@Component
public class PatrocinadorMapperImpl implements PatrocinadorMapper{
	
	@Override
	public Patrocinador mapToModel(PatrocinadorDTO dto) {
		final Patrocinador patrocinador = new Patrocinador();
		patrocinador.setName(dto.getName());
		patrocinador.setId(dto.getId());
		return patrocinador;
	}

	@Override
	public PatrocinadorDTO mapToDTO(Patrocinador model) {
		PatrocinadorDTO userdto = null;
		if(model != null) { 
			userdto = new PatrocinadorDTO();
			userdto.setId(model.getId());
			userdto.setName(model.getName());
		}
		return userdto;
	}

	@Override
	public List<PatrocinadorDTO> mapToDTO(List<Patrocinador> models) {
		return models.parallelStream().map(this::mapToDTO)
				.collect(Collectors.toList());
	}

}
