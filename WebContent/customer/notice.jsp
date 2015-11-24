<%@page import="com.newlecture.webprj.vo.Notice"%>
<%@page import="java.util.List"%>
<%@page import="com.newlecture.webprj.dao.mybatis.MyBatisNoticeDao"%>
<%@page import="com.newlecture.webprj.dao.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
NoticeDao dao = new MyBatisNoticeDao();
List<Notice>list = dao.getNotices(1, "Title","");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>notice</title>
<link href="../css/reset.css" type="text/css" rel="stylesheet">
<link href="../css/style.css" type="text/css" rel="stylesheet">

<!-- <style>

/* h1,h2 {
	color: blue;
} */


.h2{
color: yellow;
}

/* #a{
color: red;
}
 */
 
/* h1[class]{
color: red;
} */

h1[class="h1"]{
color: green;
}
.h1{
color: red;
}

/* section h1{ 
color: red;
}
 */
 
 /* 우선순위 테스트
 h1{color:red};
 #a{color:blue};
 .b{color:pink}; */
 
 /* 우선순위 테스트2 
 section>.b{color:green};
  h1.b{color:blue}; 
 .b{color:pink}; */
 
</style> -->
</head>


<body>
	<header id="header">
		<div class="content-container">
			<!-- <h1>
			<img src="../images/logo.png" alt="newlecture" />
		</h1>

		<section>
			<h1 id="a" class ="b">헤더</h1>

			<nav>
				<h1 style="color: blue; font-size: 5px;">메인메뉴</h1>
				<ul>
					<li><a href="">학습가이드</a></li>
					<li><a href="">뉴렉과정</a></li>
					<li><a href="">강좌선택</a></li>
				</ul>
			</nav>
			<section>
				<h1 class = "h2">강좌검색</h1>
				<form>
					<fieldset>
						<legend>강좌 검색 필드</legend>
						<label>강좌명</label><input type="text" /> <input type="submit"
							value="검색">
					</fieldset>
				</form>
			</section>
			<nav>
				<h1>회원메뉴</h1>
				<ul>
					<li><a href="">HOME</a></li>
					<li><a href="">로그인</a></li>
					<li><a href="">회원가입</a></li>
				</ul>
			</nav>
			<nav>
				<h1>마이메뉴</h1>
				<ul>
					<li><a href=""><img src="../images/txt-mypage.png"
							alt="마이페이지" /></a></li>
					<li><a href=""><img src="../images/txt-customer.png"
							alt="고객센터" /></a></li>
				</ul>
			</nav>
		</section> -->
		</div>
	</header>

	<div id="visual">
		<div class="content-container"></div>
	</div>

	<div id="body">
		<div class="content-container clearfix">
			<!-- hello -->
			<aside id="aside">
				<h1>고객센터</h1>
				<nav>
					<h1>고객센터 메뉴</h1>
					<ul id="c">
						<li><a href="">공지사항</a></li>
						<li><a href="">1:1 고객문의</a></li>
						<li><a href="">학습안내</a></li>
					</ul>
				</nav>
				<nav>
					<h1>추천사이트</h1>
					<ul>
						<li><a href="">ANSWERIS</a></li>
						<li><a href="">W3C</a></li>
						<li><a href="">Microsoft</a></li>
					</ul>
				</nav>
			</aside>

			<main id="main">
			<h2>공지사항</h2>
			<h3>공지사항 검색</h3>
			<form>
				<fieldset>
					<legend>공지사항 검색 필드</legend>
					<label>분류</label> <select>
						<option>분류선택</option>
						<option>제목</option>
						<option>작성자</option>
					</select> <label>검색어</label> <input type="text" /> <input type="submit"
						value="검색">
				</fieldset>
			</form>
			<h3>공지사항 목록</h3>
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
				<%for(Notice n: list) {%>
					<tr>
						<td><%=n.getCode()%></td>
						<td><%=n.getTitle()%>[<%=n.getCmtCount()%>]</td>
						<td><%=n.getWriterName()%></td>
						<td><%=n.getRegDate()%></td>
						<td><%=n.getHit()%></td>
					</tr>
				
					<%} %>
				</tbody>
			</table>

			<h3>목록의 현재 위치</h3>
			<p>1/2pages</p>
			<h3>페이저</h3>
			<p>1</p>
			<p>1</p>
			<p>1</p>
			<p>1</p>
			<p>1</p>
			<p>1</p>
			<p>1</p>
			<p>1</p>
			<p>1</p>
			
			<div id="test">검색 바?	</div>
			
			</main>
			<!-- <div style="background: purple; width: 100px; height: 100px; clear:left">test</div> -->

		</div>
	</div>

	<footer id="footer">
		<div class="content-container">
			<!-- <section>
			<h1>사이트정보</h1>
			<section>
				<h1>회사 정보</h1>
				<dl>
					<dt>주소:</dt>
					<dd>서울특별시 동대문구 장안1동 423-4번지 윤진빌딩 4층</dd>
					<dt>관리자메일:</dt>
					<dd>admin@newlecture.com</dd>
					<dt>사업자 등록번호:</dt>
					<dd>132-18-46763</dd>
				</dl>
			</section>
			<section>
				<h1>저작권 정보</h1>
				<p>Copyright ⓒ newlecture.com 2012-2014 All Right Reserved.
					Contact admin@newlecture.com for more information</p>
			</section>
		</section> -->
		</div>
	</footer>
	<!-- <div id="test">
		검색 바?
	</div> -->



</body>


</html>