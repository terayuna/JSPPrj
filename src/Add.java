
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/aaa/Add")
public class Add extends HttpServlet {
	protected void service/* doPost */(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		String _x = request.getParameter("x");
		String _y = request.getParameter("y");

		int x = 0;
		int y = 0;

		if (request.getMethod().equals("POST")) {
			if (_x != null && !_x.equals(""))

				x = Integer.parseInt(_x);
			if (_y != null && !_y.equals(""))
				y = Integer.parseInt(_y);

			int sum = x + y;
			out.printf("result: %d", sum);
		}
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("</head>");
		out.write("<body>");
		out.write("	<form action=\"../aaa/Add\" method=\"post\">");
		out.write("		<ul>");
		out.write("			<li><label for=\"x\">X:</label><input name=\"x\"/></li>");
		out.write("			<li><label for=\"y\">Y:</label><input name=\"y\"/></li>");
		out.write("		</ul>");
		out.write("		<p><input type=\"submit\" value=\"����\"/></p>");
		out.write("	</form>");
		out.write("</body>");
		out.write("</html>");
		out.close();

	}

}

/* post��û ���(��� ��û�ϴ°� ��õǾ����������� ��û�ϴµ� ��Ŀ��) */
/* ���⼭ �����ϸ� get��û�̶� ������ �� */
/*if (request.getMethod().equals("POST"))�� ����ؼ� service ����� ����ϸ� ���⼭ ���� ����*/
