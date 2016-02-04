package com.newlecture.webprj.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
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
      
      Gson gson = new Gson();
      out.println(gson.toJson(list));
      
      /*StringBuilder builder=new StringBuilder();
      builder.append("[");
      
      for(int i=0;i<list.size();i++)
      {
         Notice n=list.get(i);
         
         //builder.append(gson.toJson(n));
         
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
         e.printStackTrace();
      }
      out.println(builder.toString());*/
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
   
   @RequestMapping("noticeRegAjax")/*@RequestParam("p[]") String[] p*/
   //JSON을 통으로 받으려면, 반드시 JSON 해석기 중 하나(GSON...)를 라이브러리로 포함시키기
   //그렇지 않을경우 415(unsupported media type)오류가 발생함
   public void noticeRegAjax(@RequestBody Notice n, PrintWriter out, Principal principal)
   {
      /*System.out.println("received: "+p.length);
      System.out.println("P[2]: "+p[2]);*/
      //System.out.println(n.getTitle());
      //n.setWriter(principal.getName());
	  n.setWriter("mimi");
      int result=noticeDao.insert(n);
      
      //데이터를 처리하는 코드
      if(result>=1)
         out.write("ok");
      else
         out.write("error");
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
   
   @RequestMapping("noticeDelAjax")
   //키와 값이 쌍으로 오는 urlEncoding으로 와야하는데
   //키가 없이 몸뚱아리만 오면 안되므로 
   //이럴 땐 @RequestBody를 써서 몸통을 그대로 쓰도록
   public void noticeDelAjax(@RequestBody String[] codes, PrintWriter out)
   {
	   int result=0;
	   
	   for(int i=0; i<codes.length; i++)
		result += noticeDao.delete(codes[i]);
	  
      //데이터를 처리하는 코드
	   if(result==codes.length)
		   out.write("ok");
	   else
		   out.write("error");
   }	
   
   @RequestMapping("noticeDel")
   public String noticeDel(String c)
   {
      noticeDao.delete(c);
      return "redirect:notice";  //뷰로 인식하지 못하도록(뷰로 가로 가면 데이터 없이 가므로)
   }
   
 //단일 파일 업로드를 위한 서버 코드 with 스프링
   @RequestMapping(value="upload", method=RequestMethod.POST)
   public @ResponseBody String upload(@RequestParam("notice-title") String title, MultipartFile file,
         HttpServletRequest request) throws IOException{   //출력도구(ex>PrintWriter)를 매개변수로 써주지 말것
            
      //1.파일을 저장할 디렉토리와 경로 설정
      ServletContext context=request.getServletContext();
      String rootPath=context.getRealPath("/customer")+"\\upload";  //현재디렉토리에 대한 물리경로 (개발경로가 아닌 배포경로)
      
      File f = new File(rootPath);
      if (!f.exists()) {
         if (f.mkdir()) {
            System.out.println("Directory is created!");
         } else {
            System.out.println("Failed to create directory!");
         }
      }
      
      System.out.println("test");
      
      //2.전송된 파일을 저장하는 로직
      String fileName=file.getOriginalFilename();
      //file.getInputStream();
      byte[] buf = file.getBytes();  // 배열로 받으면 서버에 부담을 줄쑤가 있음
      
      //2-1.저장할 파일이 있는지 등의 검사를 해야함..그치만 일단 생략
      //2-2.파일 저장
      FileOutputStream fout=new FileOutputStream(rootPath+File.separator+fileName);
      fout.write(buf);
      fout.close();
      
      //System.out.println(rootPath);
      
      //return "redirect:../content/javascript/app/views/fileupload.html";
      return "You successfully uploaded file="+rootPath+File.separator+fileName;
   }
}