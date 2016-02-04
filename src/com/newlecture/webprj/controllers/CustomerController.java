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
   
   
   /*�ʿ��� ���尴ü�� �Ʒ� �Լ��� �Ű������� �־��ټ� ����*/
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
      
      model.addAttribute("notice", notice);  // ���� �Խñ�
      model.addAttribute("prev", prev);  // ����
      model.addAttribute("next", next);  // ����
      
      return "customer/noticeDetail";
   }
   
   @RequestMapping("noticeRegAjax")/*@RequestParam("p[]") String[] p*/
   //JSON�� ������ ��������, �ݵ�� JSON �ؼ��� �� �ϳ�(GSON...)�� ���̺귯���� ���Խ�Ű��
   //�׷��� ������� 415(unsupported media type)������ �߻���
   public void noticeRegAjax(@RequestBody Notice n, PrintWriter out, Principal principal)
   {
      /*System.out.println("received: "+p.length);
      System.out.println("P[2]: "+p[2]);*/
      //System.out.println(n.getTitle());
      //n.setWriter(principal.getName());
	  n.setWriter("mimi");
      int result=noticeDao.insert(n);
      
      //�����͸� ó���ϴ� �ڵ�
      if(result>=1)
         out.write("ok");
      else
         out.write("error");
   }
   
   
   @RequestMapping(value="noticeReg", method=RequestMethod.GET)
   public String noticeReg(HttpSession session){
      
      /*if(session.getAttribute("mid")==null)
         return "redirect:../joinus/login?returnUrl=/customer/noticeReg";*/
      
      return "customer/noticeReg";  // view�� �����ؾ� �ϴµ�,,,
   }
   
   @RequestMapping(value="noticeReg", method=RequestMethod.POST)
   public String noticeReg(Notice n, Principal principal)  // form�� name�� ���� �������̸� ������ request �� �ʿ� ����
   {
      
      n.setWriter(principal.getName());
      noticeDao.insert(n);
      /*System.out.println("title: "+n.getTitle());
      System.out.println("content: "+n.getContent());*/
      return "redirect:notice";   // �����͸� ������ view�� ���� ���� controller ȣ��
   }
   
   
   //�� �����ÿ��� ��������
   @RequestMapping("noticeEdit")
   public String noticeEdit(String code, HttpSession session)
   {
      /*if(session.getAttribute("mid")==null)
         return "redirect:../joinus/login?returnUrl=/customer/noticeEdit";*/
      
      return "customer/noticeEdit";  // view�� �����ؾ� �ϴµ�,,,
   }
   
   @RequestMapping("noticeDelAjax")
   //Ű�� ���� ������ ���� urlEncoding���� �;��ϴµ�
   //Ű�� ���� ���׾Ƹ��� ���� �ȵǹǷ� 
   //�̷� �� @RequestBody�� �Ἥ ������ �״�� ������
   public void noticeDelAjax(@RequestBody String[] codes, PrintWriter out)
   {
	   int result=0;
	   
	   for(int i=0; i<codes.length; i++)
		result += noticeDao.delete(codes[i]);
	  
      //�����͸� ó���ϴ� �ڵ�
	   if(result==codes.length)
		   out.write("ok");
	   else
		   out.write("error");
   }	
   
   @RequestMapping("noticeDel")
   public String noticeDel(String c)
   {
      noticeDao.delete(c);
      return "redirect:notice";  //��� �ν����� ���ϵ���(��� ���� ���� ������ ���� ���Ƿ�)
   }
   
 //���� ���� ���ε带 ���� ���� �ڵ� with ������
   @RequestMapping(value="upload", method=RequestMethod.POST)
   public @ResponseBody String upload(@RequestParam("notice-title") String title, MultipartFile file,
         HttpServletRequest request) throws IOException{   //��µ���(ex>PrintWriter)�� �Ű������� ������ ����
            
      //1.������ ������ ���丮�� ��� ����
      ServletContext context=request.getServletContext();
      String rootPath=context.getRealPath("/customer")+"\\upload";  //������丮�� ���� ������� (���߰�ΰ� �ƴ� �������)
      
      File f = new File(rootPath);
      if (!f.exists()) {
         if (f.mkdir()) {
            System.out.println("Directory is created!");
         } else {
            System.out.println("Failed to create directory!");
         }
      }
      
      System.out.println("test");
      
      //2.���۵� ������ �����ϴ� ����
      String fileName=file.getOriginalFilename();
      //file.getInputStream();
      byte[] buf = file.getBytes();  // �迭�� ������ ������ �δ��� �پ��� ����
      
      //2-1.������ ������ �ִ��� ���� �˻縦 �ؾ���..��ġ�� �ϴ� ����
      //2-2.���� ����
      FileOutputStream fout=new FileOutputStream(rootPath+File.separator+fileName);
      fout.write(buf);
      fout.close();
      
      //System.out.println(rootPath);
      
      //return "redirect:../content/javascript/app/views/fileupload.html";
      return "You successfully uploaded file="+rootPath+File.separator+fileName;
   }
}