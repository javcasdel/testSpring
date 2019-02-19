package com.pgt.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pgt.proyecto.mapper.ClubMapper;
import com.pgt.proyecto.model.Club;
import com.pgt.proyecto.service.ClubService;

@Controller
@RequestMapping("/club")
public class ClubViewController {
	
	@Autowired
	ClubService clubservice;
	
	@Autowired
	ClubMapper clubMapper;
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("club", new Club());
		return "club/create";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("club") Club club, Model model) {
		try {
			if(club.getId() == null) {
				clubservice.create(clubMapper.mapToDTO(club));
			
			} else {
				clubservice.update(club.getId(), clubMapper.mapToDTO(club));				
			}
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while saving data");
			return "club/list";
		}

		return "redirect:/club/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam(value = "id", required = true) Integer id, Model model) {
		try {
			Club club = clubMapper.mapToModel(clubservice.findById(id));
			club.setId(id);
			model.addAttribute("club", club);
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while accessing data");
			return "club/list";
		}

		return "club/create";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(value = "id", required = true) Integer id, Model model) {
		try {
			clubservice.delete(id);
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while accessing data");
			return "club/list";
		}

		return "redirect:/club/list";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(value = "name", required = false) String name, Model model) {
		model.addAttribute("clubs", clubservice.findAll(name == null || name.isEmpty()? null : name));
		return "club/list";
	}
}
