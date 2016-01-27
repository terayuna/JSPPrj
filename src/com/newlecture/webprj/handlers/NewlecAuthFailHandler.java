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
		System.out.println("��������");
		response.sendRedirect("/JSPPrj1/joinus/login"); 
		
		/*request.setAttribute("error", "���̵� �Ǵ� ��й�ȣ�� �߸��Ǿ����ϴ�.");
		request.getRequestDispatcher("/joinus/login").forward(request, response);*/
		
		//super.onAuthenticationFailure(request, response, exception);
	}
}
