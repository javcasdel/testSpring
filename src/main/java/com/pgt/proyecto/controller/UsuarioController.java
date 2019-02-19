package com.pgt.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pgt.proyecto.dto.UsuarioDTO;
import com.pgt.proyecto.mapper.UsuarioMapper;
import com.pgt.proyecto.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioservice;
	
	@Autowired
	UsuarioMapper usuarioMapper;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new UsuarioDTO());
		return "usuario/login";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("usuario", new UsuarioDTO());
		return "usuario/create";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("usuario") UsuarioDTO usuario, Model model) {
		try {
			//Check repeated usernames
			if(!usuarioservice.findAll(usuario.getName()).isEmpty()) {
				throw new RuntimeException("Repeated username");
			}
			
			//Hash the password
			usuario.setPassword(DigestUtils.md5DigestAsHex(usuario.getPassword().getBytes()));
			
			//Save the user
			usuarioservice.create(usuario);
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error while saving data");
			return "usuario/create";
		}

		return "redirect:/usuario/login";
	}
	
	@PostMapping("/check")
	public String check(@ModelAttribute("usuario") UsuarioDTO usuario, Model model) {
		if(!usuarioservice.findLogin(usuario.getName(), DigestUtils.md5DigestAsHex(usuario.getPassword().getBytes()))) {
			model.addAttribute("errorMessage", "Invalid login data");
			return "usuario/login";
		}
			

		return "redirect:/club/list";
	}
}
