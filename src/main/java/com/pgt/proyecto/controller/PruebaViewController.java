package com.pgt.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pgt.proyecto.dto.PruebaDTO;
import com.pgt.proyecto.mapper.PruebaMapper;
import com.pgt.proyecto.service.PruebaService;

@Controller
@RequestMapping("/prueba")
public class PruebaViewController {
	
	@Autowired
	PruebaService pruebaservice;
	
	@Autowired
	PruebaMapper pruebaMapper;
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("prueba", new PruebaDTO());
		return "prueba/create";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("prueba") PruebaDTO prueba, Model model) {
		try {
			if(prueba.getId() == null) {
				pruebaservice.create(prueba);
			
			} else {
				pruebaservice.update(prueba.getId(), prueba);				
			}
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while saving data");
			return "prueba/list";
		}

		return "redirect:/prueba/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam(value = "id", required = true) Integer id, Model model) {
		try {
			PruebaDTO prueba = pruebaservice.findById(id);
			prueba.setId(id);
			model.addAttribute("prueba", prueba);
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while accessing data");
			return "prueba/list";
		}

		return "prueba/create";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(value = "id", required = true) Integer id, Model model) {
		try {
			pruebaservice.delete(id);
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while accessing data");
			return "prueba/list";
		}

		return "redirect:/prueba/list";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(value = "name", required = false) String name, Model model) {
		model.addAttribute("pruebas", pruebaservice.findAll(name == null || name.isEmpty()? null : name));
		return "prueba/list";
	}
}
