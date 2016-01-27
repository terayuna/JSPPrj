
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

		response.setCharacterEncoding("UTF-8");	/*웹 브라우저가 파라미터 값을 인코딩 할 때 사용한 캐릭터 셋을 이용해서 디코딩할 때 사용할 캐릭터 셋 지정*/
		response.setContentType("text/html; charset=UTF-8");	/*생성할 문서의 타입을 지정*/

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
		out.write("		<p><input type=\"submit\" value=\"덧셈\"/></p>");
		out.write("	</form>");
		out.write("</body>");
		out.write("</html>");
		out.close();

	}

}

/*
 request(클라이언트(웹브라우저)가 전송한 요청 정보를 제공): 입력
 1.get방식 전송(달라는게 있는 요청(옵션있음))의 입력- 주소 뒤에 ?cnt=20(쿼리 스트링)
 	url 뒷부분에 ?와 함께 파라미터를 붙여서 전송
 2.post방식 전송(어떤걸 요청하는게 명시되어있지않지만 요청하는데 포커스) - ex)웹 브라우저 폼에 입력한 값을 처리할 경우
  	데이터 영역을 이용해서 파라미터를 전송
  
  
 response:출력
 
 
 
 
 오버라이드 함수가 doPost이면 request.getParameter()를 사용할 때 get요청이라 오류가 남 
 하지만, if (request.getMethod().equals("POST"))를 사용해서 service 방법을 사용하면 여기서 실행 가능
 

/*톰캣(웹컨테이너:웹 어플리케이션(jsp와 서블릿)을 실행시켜주는 컨테이너) 
1.웹서버 역할 
2.WAS(Web Application Server)역할 - JSP의 동적인 부분을 처리해줄수 있으면서  Apache 웹서버가 하는 정적인 부분(html같은)도 같이 할수 있다.


<서블릿을 직접 작성 할 경우>
workspace(서블릿 소스 코드) -> 컴파일 -> 클래스 파일을 설정해 놓은 톰캣 폴더에서 생성(WEB-INF\classes)
-> 웹 어플리케이션에 서블릿 등록(@WebServlet 어노테이션 사용하면 컨테이너(톰캣)가 알아서 등록) ->웹컨테이너(톰켓) 시작
->웹 브라우저가 열림(사용자가 요청할 수 있는 환경이 준비 됨): url을 통해서 톰캣에게 문서를 요청 
문서 종류: 1.static 문서(ex:html) 		2. dynamic 문서 

static 문서를 요청할 경우: 톰캣은 홈디렉토리를 뒤져서 요청한 것을 보내줌
dynamic 문서일 경우: url이 맵핑이 되있음, 서블릿을 직접 요청하게 됨
하지만,확장자 jsp를 요청하게 될 경우(서블릿이 없는 상태, jsp파일 열어보면 순수하게 html문서만 있는 상태) 
-> .java파일이 동적으로 만들어짐(서블릿코드가 만들어짐)->동적으로 컴파일 됨->실행(우리가 직접 작성한 서블릿이 실행되는 것 처럼)

html을 확장자 jsp로 했을 때 실행속도가 더 느리지만 장점은 서블릿 코드를 우리가 직접 만들 필요 없이 jasper 알아서 생성됨
생성된 서블릿이 톰캣  어느 디렉토리에 들어있는지 알 필요가 없이 지시서(jsp)에다가 내가 필요한 코드를 넣어 지시하는 방법만 알면 됨

*실행하기위해서는 배포를 먼저 해야하고, 배포해서 사용자 요청이 들어오면 동적인 문서(서블릿)을 직접 만들 수 도 있고 jasper에게 만들라고 할 수 도 있음(요청할 때 만들어 짐)




서블릿: 웹 개발 표준에 따라 만든 클래스 (실행 코드 방식)
만드는 과정
1.자바 코드 작성
2.코드 컴파일해서 클래스 파일 생성
서블릿 단점: 화면에 출력되는 데이터를 조금만 바꾸려해도 코드를 수정하고 컴파일하고 클래스를 알맞은 곳에 복사해주는 작업을 반복해야 함
****자바 소스 코드 컴파일하고 web.xml파일에 등록(or 어노테이션 사용)하는 것들이 복잡. 또한, 응답 화면에서 텍스트 데이터 출력해주는 코드 역시 복잡(위 코드와 같이)



jsp(java server page): 서블릿 단점 보완(코드 수정하면 바로 변경 내역 반영됨) - 스크립트 방식
1.코드형태(컴파일 되지 않은 스크립트 코드)
2.실행 방식(스크립트 코드 해석 뒤 실행)
	웹 브라우저 요청 전송->웹 서버 요청 받음 -> 스크립트 코드 번역 -> 번역된 코드 실행
****별다른 설정이나 컴파일 과정 없이 간단하게 JSP파일 작성하고 실행 가능


생산공장(서버) 사용(클라이언트) 떨어져 있어서 인프라 필요
인프라(웹)
웹클라언트 - 웹서버
웹서버가 자바프로그램을 실행할지(web 컨테이너) 그냥 정적 문서를 실행할지
*/





