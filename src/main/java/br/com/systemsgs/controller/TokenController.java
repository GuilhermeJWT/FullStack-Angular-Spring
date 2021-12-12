package br.com.systemsgs.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.config.SystemsGsPropertyProfile;

@RestController
@RequestMapping(value = "/api/tokens")
public class TokenController {
	
	@Autowired
	private SystemsGsPropertyProfile systemsGsPropertyProfile;
	
	@DeleteMapping(value = "/revoke")
	public void revoke(HttpServletRequest request, HttpServletResponse respose) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(systemsGsPropertyProfile.getSeguranca().isEnableHttps());
		cookie.setPath(request.getContextPath() + "/oath/token");
		
		respose.addCookie(cookie);
		respose.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
