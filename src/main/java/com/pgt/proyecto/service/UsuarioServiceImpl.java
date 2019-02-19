package com.pgt.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgt.proyecto.dao.UsuarioDAO;
import com.pgt.proyecto.dto.UsuarioDTO;
import com.pgt.proyecto.mapper.UsuarioMapper;
import com.pgt.proyecto.model.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioDAO dao;
	
	@Autowired
	UsuarioMapper mapper;
	
	public List<UsuarioDTO> findAll(String name){
		return name!= null ? mapper.mapToDTO(dao.findByName(name)) : mapper.mapToDTO(dao.findAll());
	}

	@Override
	public UsuarioDTO findById(Integer id){
		return mapper.mapToDTO(dao.findById(id).orElse(null));
	}

	@Override
	public UsuarioDTO create(UsuarioDTO usuarioDTO) {
		return mapper.mapToDTO(dao.save(mapper.mapToModel(usuarioDTO)));
	}

	@Override
	public void update(Integer id, UsuarioDTO usuarioDTO) {
		Usuario usuario = mapper.mapToModel(usuarioDTO);
		usuario.setId(id);
		dao.saveAndFlush(usuario);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public Boolean findLogin(String name, String password) {
		return dao.findLogin(name, password) != null;
	}
}
