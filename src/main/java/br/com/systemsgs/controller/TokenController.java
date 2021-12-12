package br.com.systemsgs.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tokens")
public class TokenController {
	
	@DeleteMapping(value = "/revoke")
	public void revoke(HttpServletRequest request, HttpServletResponse respose) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(false); /*em produção vai ser True*/
		cookie.setPath(request.getContextPath() + "/oath/token");
	}
}
