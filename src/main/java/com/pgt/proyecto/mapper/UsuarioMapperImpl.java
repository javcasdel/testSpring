package com.pgt.proyecto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.pgt.proyecto.dto.UsuarioDTO;
import com.pgt.proyecto.model.Usuario;

@Component
public class UsuarioMapperImpl implements UsuarioMapper{
	
	@Override
	public Usuario mapToModel(UsuarioDTO dto) {
		final Usuario usuario = new Usuario();
		usuario.setName(dto.getName());
		usuario.setPassword(dto.getPassword());
		usuario.setRole(dto.getRole());
		return usuario;
	}

	@Override
	public UsuarioDTO mapToDTO(Usuario model) {
		UsuarioDTO usuariodto = null;
		if(model != null) {
			usuariodto = new UsuarioDTO();
			usuariodto.setId(model.getId());
			usuariodto.setName(model.getName());
			usuariodto.setPassword(model.getPassword());
			usuariodto.setRole(model.getRole());		
		}
		return usuariodto;
	}

	@Override
	public List<UsuarioDTO> mapToDTO(List<Usuario> models) {
		return models.parallelStream().map(this::mapToDTO)
		.collect(Collectors.toList());
	}

}
