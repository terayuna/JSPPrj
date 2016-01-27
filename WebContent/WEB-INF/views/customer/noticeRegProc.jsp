<%@page import="com.newlecture.webprj.dao.mybatis.MyBatisNoticeFileDao"%>
<%@page import="com.newlecture.webprj.dao.NoticeFileDao"%>
<%@page import="com.newlecture.webprj.vo.NoticeFile"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.newlecture.webprj.vo.Notice"%>
<%@page import="com.newlecture.webprj.dao.NoticeDao"%>
<%@page import="com.newlecture.webprj.dao.mybatis.MyBatisNoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//post된 사용자 입력값 받아옴
	
	
	/* title=new String(title.getBytes("ISO-8859-1"),"UTF-8");
	content=new String(content.getBytes("ISO-8859-1"),"UTF-8"); */
	
	ServletContext ctx=request.getServletContext();
	String path=ctx.getRealPath("/customer/upload");
	out.println(path);
	
 	MultipartRequest req=new MultipartRequest(request
			,path
			,1024*1024*10
			,"UTF-8"
			,new DefaultFileRenamePolicy());
 	
 	/* -----------notice info----------- */
 	String title=req.getParameter("title");
	String content=req.getParameter("content");
	
	NoticeDao noticeDao=new MyBatisNoticeDao();
	Notice n=new Notice();
	n.setTitle(title);
	n.setContent(content);
	n.setWriter("newlec");
	
	int a=noticeDao.insert(n);
/* 	if(a==1)
		response.sendRedirect("notice.jsp"); */
	
	/* -----------notice file info----------- */
	String src=req.getOriginalFileName("file");
	
	NoticeFile file=new NoticeFile();
	file.setNoticeCode(noticeDao.getLastCode());
	file.setSrc(src);
	
	NoticeFileDao fileDao=new MyBatisNoticeFileDao();
	a=fileDao.insert(file);
	
	
	//db에 저장

%>

	<%-- result : <%=a%> --%>