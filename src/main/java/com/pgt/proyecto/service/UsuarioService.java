package com.pgt.proyecto.service;

import java.util.List;

import com.pgt.proyecto.dto.UsuarioDTO;

public interface UsuarioService {

	List<UsuarioDTO> findAll(String name);
	
	Boolean findLogin(String name, String password);

	UsuarioDTO findById(Integer id);

	UsuarioDTO create(UsuarioDTO usuarioDTO);

	void update(Integer id, UsuarioDTO usuarioDTO);

	void delete(Integer id);
	
}
