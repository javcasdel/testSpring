package com.pgt.proyecto;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String path = request.getServletPath();
		
		if(!path.equals("/usuario/login") && !path.equals("/usuario/create")) {
			Cookie authKey = Arrays.stream(request.getCookies()).filter(x -> x.getName().equals("auth")).findFirst().orElse(null);
			
			
			if(authKey == null /* || !validKey(authKey.getValue())*/) {
				response.sendRedirect("/usuario/login");
			}

			System.out.println(path + "   " + Arrays.stream(request.getCookies()).map(x -> x.getName()).collect(Collectors.toList()));
		}
		
		return true;
	}
}
