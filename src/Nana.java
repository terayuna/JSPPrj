import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Nana extends HttpServlet
{
	public void service(
		HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException{
		
		int x=3;
		int y=5;
		int result = x+y;
		System.out.println(result);
			
		OutputStream os = response.getOutputStream();
		PrintWriter out = new PrintWriter(os);
		out.println(result);
		out.close();
		os.close();
		
	} 
}

