
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/aaa/Add")
public class Add extends HttpServlet {
	
	protected void service/* doPost */(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");	/*�� �������� �Ķ���� ���� ���ڵ� �� �� ����� ĳ���� ���� �̿��ؼ� ���ڵ��� �� ����� ĳ���� �� ����*/
		response.setContentType("text/html; charset=UTF-8");	/*������ ������ Ÿ���� ����*/

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

/*
 request(Ŭ���̾�Ʈ(��������)�� ������ ��û ������ ����): �Է�
 1.get��� ����(�޶�°� �ִ� ��û(�ɼ�����))�� �Է�- �ּ� �ڿ� ?cnt=20(���� ��Ʈ��)
 	url �޺κп� ?�� �Բ� �Ķ���͸� �ٿ��� ����
 2.post��� ����(��� ��û�ϴ°� ��õǾ����������� ��û�ϴµ� ��Ŀ��) - ex)�� ������ ���� �Է��� ���� ó���� ���
  	������ ������ �̿��ؼ� �Ķ���͸� ����
  
  
 response:���
 
 
 
 
 �������̵� �Լ��� doPost�̸� request.getParameter()�� ����� �� get��û�̶� ������ �� 
 ������, if (request.getMethod().equals("POST"))�� ����ؼ� service ����� ����ϸ� ���⼭ ���� ����
 

/*��Ĺ(�������̳�:�� ���ø����̼�(jsp�� ����)�� ��������ִ� �����̳�) 
1.������ ���� 
2.WAS(Web Application Server)���� - JSP�� ������ �κ��� ó�����ټ� �����鼭  Apache �������� �ϴ� ������ �κ�(html����)�� ���� �Ҽ� �ִ�.


<������ ���� �ۼ� �� ���>
workspace(���� �ҽ� �ڵ�) -> ������ -> Ŭ���� ������ ������ ���� ��Ĺ �������� ����(WEB-INF\classes)
-> �� ���ø����̼ǿ� ���� ���(@WebServlet ������̼� ����ϸ� �����̳�(��Ĺ)�� �˾Ƽ� ���) ->�������̳�(����) ����
->�� �������� ����(����ڰ� ��û�� �� �ִ� ȯ���� �غ� ��): url�� ���ؼ� ��Ĺ���� ������ ��û 
���� ����: 1.static ����(ex:html) 		2. dynamic ���� 

static ������ ��û�� ���: ��Ĺ�� Ȩ���丮�� ������ ��û�� ���� ������
dynamic ������ ���: url�� ������ ������, ������ ���� ��û�ϰ� ��
������,Ȯ���� jsp�� ��û�ϰ� �� ���(������ ���� ����, jsp���� ����� �����ϰ� html������ �ִ� ����) 
-> .java������ �������� �������(�����ڵ尡 �������)->�������� ������ ��->����(�츮�� ���� �ۼ��� ������ ����Ǵ� �� ó��)

html�� Ȯ���� jsp�� ���� �� ����ӵ��� �� �������� ������ ���� �ڵ带 �츮�� ���� ���� �ʿ� ���� jasper �˾Ƽ� ������
������ ������ ��Ĺ  ��� ���丮�� ����ִ��� �� �ʿ䰡 ���� ���ü�(jsp)���ٰ� ���� �ʿ��� �ڵ带 �־� �����ϴ� ����� �˸� ��

*�����ϱ����ؼ��� ������ ���� �ؾ��ϰ�, �����ؼ� ����� ��û�� ������ ������ ����(����)�� ���� ���� �� �� �ְ� jasper���� ������ �� �� �� ����(��û�� �� ����� ��)




����: �� ���� ǥ�ؿ� ���� ���� Ŭ���� (���� �ڵ� ���)
����� ����
1.�ڹ� �ڵ� �ۼ�
2.�ڵ� �������ؼ� Ŭ���� ���� ����
���� ����: ȭ�鿡 ��µǴ� �����͸� ���ݸ� �ٲٷ��ص� �ڵ带 �����ϰ� �������ϰ� Ŭ������ �˸��� ���� �������ִ� �۾��� �ݺ��ؾ� ��
****�ڹ� �ҽ� �ڵ� �������ϰ� web.xml���Ͽ� ���(or ������̼� ���)�ϴ� �͵��� ����. ����, ���� ȭ�鿡�� �ؽ�Ʈ ������ ������ִ� �ڵ� ���� ����(�� �ڵ�� ����)



jsp(java server page): ���� ���� ����(�ڵ� �����ϸ� �ٷ� ���� ���� �ݿ���) - ��ũ��Ʈ ���
1.�ڵ�����(������ ���� ���� ��ũ��Ʈ �ڵ�)
2.���� ���(��ũ��Ʈ �ڵ� �ؼ� �� ����)
	�� ������ ��û ����->�� ���� ��û ���� -> ��ũ��Ʈ �ڵ� ���� -> ������ �ڵ� ����
****���ٸ� �����̳� ������ ���� ���� �����ϰ� JSP���� �ۼ��ϰ� ���� ����


�������(����) ���(Ŭ���̾�Ʈ) ������ �־ ������ �ʿ�
������(��)
��Ŭ���Ʈ - ������
�������� �ڹ����α׷��� ��������(web �����̳�) �׳� ���� ������ ��������
*/





