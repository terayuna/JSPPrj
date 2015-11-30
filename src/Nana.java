import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/hi")
public class Nana extends HttpServlet
{
	/*service는 get과 post 둘다 가능*/
	public void service(
		HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException{
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		/*OutputStream os = response.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);*/
		
		PrintWriter out = response.getWriter();
		String _cnt =request.getParameter("cnt"); 
		
		int cnt =2;
		if(_cnt != null && !_cnt.equals(""))
			cnt = Integer.parseInt(_cnt);
		
		for(int i=0; i<cnt; i++)
			out.printf("수연아!!<br />");
		
		out.close();
	} 
}

/*get요청(달라는게 있는 요청(옵션있음))의 입력- 주소 뒤에 ?cnt=20(쿼리 스트링)*/