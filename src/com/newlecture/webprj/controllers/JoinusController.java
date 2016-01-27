package com.newlecture.webprj.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newlecture.webprj.dao.MemberDao;
import com.newlecture.webprj.vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinusController {
	
	@Autowired
	private MemberDao memberDao;
	
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login()
	{
		return "joinus/login";
	}
	
	/*@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(
			@Param("user-name") String userName, 
			String Password, Model model, HttpSession session) throws SQLException
	{
		Member m=memberDao.getMember(userName);
		String error=null;
		
		if(m==null)
			error="사용자 아이디 또는 비밀번호가 올바르지 않습니다.";
		else if(!m.getPwd().equals(Password))
			error="사용자 아이디 또는 비밀번호가 올바르지 않습니다.";
		
		
		if(error!=null)
		{
			model.addAttribute("error",error);
			return "joinus/login";
		}
		
		//인증 성공 //인증정보를 저장 어디에?? 세션? 쿠키?
		session.setAttribute("mid", userName);
		
		//리턴 파라미터 있는지 검사해야함
		return "redirect:customer/notice";
	}*/
}