package com.newlecture.webprj.controllers;

import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecture.webprj.dao.NoticeDao;
import com.newlecture.webprj.dao.mybatis.MyBatisNoticeDao;
import com.newlecture.webprj.vo.Notice;

//POJO
@Controller
@RequestMapping("/customer/")
public class CustomerController {
	
	
	@Autowired
	private NoticeDao noticeDao;
	
	
	/*필요한 내장객체를 아래 함수의 매개변수로 넣어줄수 있음*/
	@RequestMapping("notice")
	public String notice(PrintWriter out, Model model, 
			String pg, String f, String q,
			HttpServletRequest request){
		
		int page=1;
		String field="Title";
		String query="";
		
		if(pg!=null && !pg.equals(""))
			page=Integer.parseInt(pg);
		if(f!=null && !f.equals(""))
			field=f;
		if(q!=null && !q.equals(""))
			query=q;
		
		/*NoticeDao dao = new MyBatisNoticeDao();*/
		//List<Notice>list = noticeDao.getNotices(1, "Title","");
		
		List<Notice>list = noticeDao.getNotices(page, field, query);
		int recordCount=noticeDao.getNoticeCount(field, query);
		model.addAttribute("recordCount", recordCount);
		
		model.addAttribute("list",list);
		//model.addAttribute("n",list.get(0));
		/*return "/WEB-INF/views/customer/notice.jsp";*/
		return "customer/notice";
		
	}

	@RequestMapping("noticeRegPartial")	
	public String noticeRegPatial(String p, Model model){
	
		
		return "/customer/noticeRegPartial";
	
	}

	
	@RequestMapping("noticePartial")	
	public String noticePatial(String pg, String f, String q, Model model){
		
		int page=1;
		String field="Title";
		String query="";
		
		if(pg!=null && !pg.equals(""))
			page=Integer.parseInt(pg);
		if(f!=null && !f.equals(""))
			field=f;
		if(q!=null && !q.equals(""))
			query=q;
		
		List<Notice>list = noticeDao.getNotices(page, field, query);
				
		model.addAttribute("list", list);

		return "/customer/noticePartial";
		//return "WEB-INF/views/customer/noticePartial.jsp";
	}
	
	@RequestMapping("noticeJSON")
	public void noticeJSON(String p, PrintWriter out){
		
		int page=1;
		if(p!=null && !p.equals(""))
			page=Integer.parseInt(p);
		
		List<Notice> list=noticeDao.getNotices(page, "TITLE", "");
		
		StringBuilder builder=new StringBuilder();
		builder.append("[");
		
		for(int i=0;i<list.size();i++)
		{
			Notice n=list.get(i);
			
			if(i==list.size()-1)
				builder.append(String.format("{\"code\":\"%s\", \"title\":\"%s\", \"writer\":\"%s\"}",
						n.getCode(),n.getTitle(),n.getWriter()));
			else
			{
				builder.append(String.format("{\"code\":\"%s\", \"title\":\"%s\", \"writer\":\"%s\"},",
						n.getCode(),n.getTitle(),n.getWriter()));
			}
		}
		
		builder.append("]");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(builder.toString());
	}
	
	
	
	@RequestMapping("noticeDetail")
	public String noticeDetail(String c, Model model){

		Notice notice=noticeDao.getNotice(c);
		
		Notice prev=noticeDao.getPrevNotice(c);
		Notice next=noticeDao.getNextNotice(c);
		
		model.addAttribute("notice", notice);  // 현재 게시글
		model.addAttribute("prev", prev);  // 이전
		model.addAttribute("next", next);  // 이후
		
		return "customer/noticeDetail";
	}
	
	@RequestMapping("noticeRegAjax")
	public void noticeRegAjax(@RequestParam("p[]") String[] p, PrintWriter out)
	{
		System.out.println("received: "+p.length);
		System.out.println("P[2]: "+p[2]);
		//데이터를 처리하는 코드
		if(p!=null)
			out.write("ok");
		else
			out.write("failed");
	}
	
	
	@RequestMapping(value="noticeReg", method=RequestMethod.GET)
	public String noticeReg(HttpSession session){
		
		/*if(session.getAttribute("mid")==null)
			return "redirect:../joinus/login?returnUrl=/customer/noticeReg";*/
		
		return "customer/noticeReg";  // view를 제공해야 하는데,,,
	}
	
	@RequestMapping(value="noticeReg", method=RequestMethod.POST)
	public String noticeReg(Notice n, Principal principal)  // form의 name과 같은 변수명이면 가져옴 request 쓸 필요 없음
	{
		
		n.setWriter(principal.getName());
		noticeDao.insert(n);
		/*System.out.println("title: "+n.getTitle());
		System.out.println("content: "+n.getContent());*/
		return "redirect:notice";   // 데이터를 포함한 view로 가기 위해 controller 호출
	}
	
	
	//글 수정시에도 마찬가지
	@RequestMapping("noticeEdit")
	public String noticeEdit(String code, HttpSession session)
	{
		/*if(session.getAttribute("mid")==null)
			return "redirect:../joinus/login?returnUrl=/customer/noticeEdit";*/
		
		return "customer/noticeEdit";  // view를 제공해야 하는데,,,
	}
	
	@RequestMapping("noticeDel")
	public String noticeDel(String c)
	{
		noticeDao.delete(c);
		return "redirect:notice";  //뷰로 인식하지 못하도록(뷰로 가로 가면 데이터 없이 가므로)
	}
}
