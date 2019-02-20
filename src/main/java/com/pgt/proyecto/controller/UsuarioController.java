package com.pgt.proyecto.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String login(@RequestParam(value = "logout", required = false, defaultValue = "false") String logout, Model model) {
		model.addAttribute("usuario", new UsuarioDTO());
		model.addAttribute("logout", logout);
		
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
	public String check(@ModelAttribute("usuario") UsuarioDTO usuario, Model model, HttpServletResponse response) {
		String key = DigestUtils.md5DigestAsHex(usuario.getPassword().getBytes());
		UsuarioDTO authUsusario = usuarioservice.findLogin(usuario.getName(), key);
		
		if(authUsusario == null) {
			model.addAttribute("errorMessage", "Invalid login data");
			return "usuario/login";
		}

		response.addCookie(new Cookie("auth", authUsusario.getId().toString()));
		return "club/list";
	}
}
