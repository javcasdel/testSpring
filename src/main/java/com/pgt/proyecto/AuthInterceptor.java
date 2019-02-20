package com.pgt.proyecto;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.pgt.proyecto.dto.UsuarioDTO;
import com.pgt.proyecto.mapper.UsuarioMapper;
import com.pgt.proyecto.service.UsuarioService;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Autowired
	UsuarioService usuarioservice;
	
	@Autowired
	UsuarioMapper usuarioMapper;
	
	static List<String> noAuthPatterns = Arrays.asList(
		"/usuario/login",
		"/usuario/create",
		"/usuario/save",
		"/usuario/check"
	);
	
	static List<String> userPatterns = Arrays.asList(
		".*"
	);
	
	static List<String> adminPatterns = Arrays.asList(
		"/.+/assign",
		"/.+/create",
		"/.+/save"
	);
	
	private boolean isValidKey(String key) {
		if(!key.matches("[1-9][0-9]*")) {
			return false;
		}
		
		if(usuarioservice.findById(Integer.valueOf(key)) == null) {
			return false;
		}
		
		return true;
	}
	
	private boolean hasAuthRole(String servletPath, String role) {		
		if(!role.equals("ADMIN") && adminPatterns.stream().anyMatch(pattern -> servletPath.matches(pattern))) {
			return false;
		}
		
		if(!role.equals("ADMIN") && !role.equals("USER") && userPatterns.stream().anyMatch(pattern -> servletPath.matches(pattern))) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String path = request.getServletPath();
		
		if(noAuthPatterns.stream().noneMatch(pattern -> path.matches(pattern))) {			
			Cookie authKey = Arrays.stream(request.getCookies()).filter(x -> x.getName().equals("auth")).findFirst().orElse(null);
			
			if(authKey == null || !isValidKey(authKey.getValue())) {
				response.sendRedirect("/usuario/login");
				return true;
			}

			UsuarioDTO usuario = usuarioservice.findById(Integer.valueOf(authKey.getValue()));
			
			if(!hasAuthRole(path, usuario.getRole())) {
				response.sendRedirect("/usuario/login");				
			}
		}
		
		return true;
	}
}
