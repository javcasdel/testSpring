package com.pgt.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pgt.proyecto.dto.PatrocinadorDTO;
import com.pgt.proyecto.service.PatrocinadorService;

@RestController
@RequestMapping("/patrocinador")
public class PatrocinadorController {
	
	@Autowired
	PatrocinadorService patrocinadorService;

	@GetMapping
	public List<PatrocinadorDTO> findAll(@RequestParam(value = "name", required = false) String name) {
		return patrocinadorService.findAll(name);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PatrocinadorDTO> findById(@PathVariable Integer id) {
	    return Optional
	            .ofNullable( patrocinadorService.findById(id) )
	            .map( user -> ResponseEntity.ok().body(user) )          
	            .orElseGet( () -> ResponseEntity.notFound().build() );  
	}

	@PostMapping
	public PatrocinadorDTO create(@RequestBody PatrocinadorDTO patrocinadorToCreate) {
		return patrocinadorService.create(patrocinadorToCreate);
	}

	@PutMapping("/{id}")
	public void update(@RequestBody PatrocinadorDTO patrocinadorToUpdate,@PathVariable Integer id) {
		patrocinadorService.update(id, patrocinadorToUpdate);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		patrocinadorService.delete(id);
	}	

	@PutMapping("/{idp}/assign/{idc}")
	public void assign(@PathVariable Integer idp, @PathVariable Integer idc) {
		patrocinadorService.assign(idp, idc, patrocinadorService.findById(idp));
	}
}


