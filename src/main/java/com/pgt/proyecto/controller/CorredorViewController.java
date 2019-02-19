package com.pgt.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pgt.proyecto.dto.CorredorDTO;
import com.pgt.proyecto.mapper.CorredorMapper;
import com.pgt.proyecto.service.ClubService;
import com.pgt.proyecto.service.CorredorService;

@Controller
@RequestMapping("/corredor")
public class CorredorViewController {
	
	@Autowired
	CorredorService corredorservice;
	
	@Autowired
	CorredorMapper corredorMapper;
	
	@Autowired
	ClubService clubService;
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("corredor", new CorredorDTO());
		model.addAttribute("clubs", clubService.findAll(null));
		return "corredor/create";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("corredor") CorredorDTO corredor, Integer idclub, Model model) {
		try {
			if(corredor.getId() == null) {
				clubService.create(corredor, idclub);
			
			} else {
				clubService.update(idclub, corredor.getId(), corredor);				
			}
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while saving data");
			return "corredor/list";
		}

		return "redirect:/corredor/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam(value = "id", required = true) Integer id, Model model) {
		try {
			CorredorDTO corredor = corredorservice.findById(id);
			corredor.setId(id);
			model.addAttribute("corredor", corredor);
			model.addAttribute("clubs", clubService.findAll(null));

			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while accessing data");
			return "corredor/list";
		}

		return "corredor/create";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(value = "id", required = true) Integer id, Model model) {
		try {
			corredorservice.delete(id);
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while accessing data");
			return "corredor/list";
		}

		return "redirect:/corredor/list";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(value = "name", required = false) String name, Model model) {
		model.addAttribute("corredores", corredorservice.findAllCorredor(null, name == null || name.isEmpty()? null : name));
		return "corredor/list";
	}
}
