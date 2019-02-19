package com.pgt.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pgt.proyecto.mapper.PatrocinadorMapper;
import com.pgt.proyecto.model.Patrocinador;
import com.pgt.proyecto.service.ClubService;
import com.pgt.proyecto.service.PatrocinadorService;

@Controller
@RequestMapping("/patrocinador")
public class PatrocinadorViewController {
	
	@Autowired
	PatrocinadorService patrocinadorservice;
	
	@Autowired
	PatrocinadorMapper patrocinadorMapper;
	
	@Autowired
	ClubService clubservice;
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("patrocinador", new Patrocinador());
		return "patrocinador/create";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("patrocinador") Patrocinador patrocinador, Model model) {
		try {
			if(patrocinador.getId() == null) {
				patrocinadorservice.create(patrocinadorMapper.mapToDTO(patrocinador));
			
			} else {
				patrocinadorservice.update(patrocinador.getId(), patrocinadorMapper.mapToDTO(patrocinador));				
			}
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while saving data");
			return "patrocinador/list";
		}

		return "redirect:/patrocinador/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam(value = "id", required = true) Integer id, Model model) {
		try {
			Patrocinador patrocinador = patrocinadorMapper.mapToModel(patrocinadorservice.findById(id));
			patrocinador.setId(id);
			model.addAttribute("patrocinador", patrocinador);
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while accessing data");
			return "patrocinador/list";
		}

		return "patrocinador/create";
	}
	
	@GetMapping("/assign")
	public String assign(@RequestParam(value = "idpatrocinador", required = true) Integer idpatrocinador, 
			@RequestParam(value = "idclub", required = true) Integer idclub, Model model) {
		try {
			patrocinadorservice.assign(idpatrocinador, idclub, patrocinadorservice.findById(idpatrocinador));
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while accessing data");
			return "patrocinador/list";
		}

		return "redirect:/patrocinador/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(value = "id", required = true) Integer id, Model model) {
		try {
			patrocinadorservice.delete(id);
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while accessing data");
			return "patrocinador/list";
		}

		return "redirect:/patrocinador/list";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(value = "name", required = false) String name, Model model) {
		model.addAttribute("patrocinadores", patrocinadorservice.findAll(name == null || name.isEmpty()? null : name));
		model.addAttribute("clubs", clubservice.findAll(null));
		return "patrocinador/list";
	}
}
