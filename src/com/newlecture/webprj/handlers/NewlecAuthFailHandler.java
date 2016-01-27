package com.newlecture.webprj.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class NewlecAuthFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, HttpServletResponse response, 
			AuthenticationException authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("인증실패");
		response.sendRedirect("/JSPPrj1/joinus/login"); 
		
		/*request.setAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
		request.getRequestDispatcher("/joinus/login").forward(request, response);*/
		
		//super.onAuthenticationFailure(request, response, exception);
	}
}
