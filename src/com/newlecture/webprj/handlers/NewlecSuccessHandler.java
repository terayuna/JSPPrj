package com.newlecture.webprj.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class NewlecSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("��������");
		request.getSession().setAttribute("uid", authentication.getName());
		response.sendRedirect("/JSPPrj1/customer/notice");
		
	}

}
