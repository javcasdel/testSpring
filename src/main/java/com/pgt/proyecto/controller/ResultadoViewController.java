package com.pgt.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pgt.proyecto.dto.ResultadoDTO;
import com.pgt.proyecto.mapper.ResultadoMapper;
import com.pgt.proyecto.service.CorredorService;
import com.pgt.proyecto.service.PruebaService;
import com.pgt.proyecto.service.ResultadoService;

@Controller
@RequestMapping("/resultado")
public class ResultadoViewController {
	
	@Autowired
	ResultadoService resultadoservice;
	
	@Autowired
	ResultadoMapper resultadoMapper;
	
	@Autowired
	PruebaService pruebaService;
	
	@Autowired
	CorredorService corredorService;
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("resultado", new ResultadoDTO());
		model.addAttribute("pruebas", pruebaService.findAll(null));
		model.addAttribute("corredores", corredorService.findAllCorredor(null, null));
		
		return "resultado/create";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("resultado") ResultadoDTO resultado, Integer idprueba, Model model) {
		try {
			if(resultado.getId() == null) {
				pruebaService.create(resultado, idprueba);
			
			} else {
				pruebaService.update(idprueba, resultado.getId(), resultado);				
			}
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while saving data");
			model.addAttribute("resultados", resultadoservice.findAllCorredor());

			return "resultado/list";
		}

		return "redirect:/resultado/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam(value = "id", required = true) Integer id, Model model) {
		try {
			ResultadoDTO resultado = resultadoservice.findById(id);
			resultado.setId(id);
			model.addAttribute("resultado", resultado);
			model.addAttribute("pruebas", pruebaService.findAll(null));
			model.addAttribute("corredores", corredorService.findAllCorredor(null, null));
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while accessing data");
			model.addAttribute("resultados", resultadoservice.findAllCorredor());
			
			return "resultado/list";
		}

		return "resultado/create";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(value = "id", required = true) Integer id, Model model) {
		try {
			resultadoservice.delete(id);
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while accessing data");
			model.addAttribute("resultados", resultadoservice.findAllCorredor());
			
			return "resultado/list";
		}

		return "redirect:/resultado/list";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(value = "name", required = false) String name, Model model) {
		model.addAttribute("resultados", resultadoservice.findAllCorredor());
		return "resultado/list";
	}
}
