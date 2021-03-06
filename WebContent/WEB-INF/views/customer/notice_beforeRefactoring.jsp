﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>


<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="../content/js/ui.js" type="text/javascript"></script>

<script>
	var param={f:'${param.f}',q:'${param.q}'};
	if(param.f=="")
		param.f="TITLE";
	
</script>

<script>
	/* var datas=[
		          {code:1, title:'<b>자바스크립트란?</b>', writer:'newlec'},
		          {code:2, title:'DOM이란?', writer:'newlec'},
		          {code:3, title:'Ajax를 이용하는 방법은?', writer:'newlec'}
	          ]; */	
	
	function dataBind(row,data){
		var tds=row.querySelectorAll("td");
		var keys=["code","title","writer"];
		
		for(var i=0;i<tds.length;i++)  //for-in문
		{
			//1.innerText
			//2.innerHTML 심으려고 하는 데이터가 태그를 포함하고 있을때 실행가능
			//3.content  
			tds[i].innerHTML=data[keys[i]];
		}		
	}

	window.onload=function(){
		
		var table=document.querySelector("#notices");
		table.style.height=table.clientHeight+"px";
		var tbody=document.querySelector("#notices tbody");
		tbody.style.height=tbody.clientHeight+"px";
		
		
		/* ==== 이벤트 심화 예제 ============================= */
		/* ---- 이벤트 버블링과 캡쳐링-------------------------- */
		var handler=function(event){
			//alert(event.currentTarget.nodeName+" clicked");
			var tr=event.target.parentNode;
			tr.style.background="red"
			alert(event.currentTarget.nodeName+" clicked");
			//event.stopPropagation();
		}
		
		/* var tds=document.querySelectorAll("#notices tbody td");
		for(var i=0;i<tds.length;i++)
			tds[i].addEventListener("click",handler); */
		/* for(var i=0;i<tds.length;i++)
			tds[i].onclick=function(event){  // event의 target은 td를 의미
				alert("td clicked");
				var tr=event.target.parentNode;
				tr.style.background="red";
		}; */
		
		/* var trs=document.querySelectorAll("#notices tbody tr");
		for(var i=0;i<trs.length;i++)
			trs[i].addEventListener("click",handler); */
		/* for(var i=0;i<trs.length;i++)
			trs[i].onclick=function(event){  // event의 target은 td를 의미
				
				alert("tr clicked");
				var tr=event.target.parentNode;
				tr.style.background="green";
		}; */
		
		var tbody=document.querySelector("#notices tbody")
		tbody.addEventListener("click",handler,true); 
		/* tbody.onclick=function(event){  // event의 target은 td를 의미
			alert("tbody clicked");
			var tr=event.target.parentNode;
			tr.style.background="blue";
		}; */
		
		/* ---------이벤트 전파를 멈추는 예제--------- */
		//tbody안의 모든 엘리먼트들이 동일한 동작을 해야 할때 tbody에 이벤트 로직을
		//대입했는데,,특정한 하나의 자식 엘리먼트가 자신의 로직을 따로 가져가야 할 경우
		var td=document.querySelector("#notices tbody tr:nth-child(2) td:nth-child(2)")
		td.onclick=function(){
			var tr=event.target.parentNode;
			tr.style.background="pink";
			alert("바뀌었다요");
			
			//event.stopPropagation();
		}
		
		/* 기본 행위 또는 Default Action을 막기위한 예제
		기본행위 : 태그가 가지고 있는 기본 행위 ex> <a/>, <input submit/> .... */
		
		//1. tbody안의 모든 a 엘리먼트 목록을 얻는다.
		var titles=document.querySelectorAll("#notices tbody td a");
		//2. 모든 a엘리먼트에 click이벤트 바인딩
		for(var i=0;i<titles.length;i++)
			titles[i].onclick=function(event){
				alert("Hello");
				event.preventDefault();
		}
		
		/* ###########이벤트 트리거를 위한 예제############ */
		var openBox=document.querySelector("#open-box");
		openBox.onclick=function(){
			var file=document.querySelector("input[type=file]");
			/* var event=new MouseEvent("click",{
				'view':window,
				'bubbles':true,
				'cancelable':true
			}); */
			var event=document.createEvent("MouseEvent");
			event.initEvent("click",true,true);
			file.dispatchEvent(event);
			
		}
		
		
		//--------------AJAX POST METHOD
		var btnWrite=document.getElementById("btn-write");
		btnWrite.onclick=function(){
			
			/* var dlg=document.createElement("div");
			dlg.style.width="100%";
			dlg.style.height="100%";
			dlg.style.position="fixed";
			dlg.style.top="0px";
			
			var screen=document.createElement("div");
			screen.style.backgroundColor="black";
			screen.style.opacity="0.3";
			screen.style.width="inherit";
			screen.style.height="inherit";
			
			var container=document.createElement("div");
			container.style.backgroundColor="#fff";
			container.style.width="720px";
			container.style.height="500px";
			container.style.position="fixed";
			container.style.top="100px";
			container.style.left="300px";
			
			dlg.appendChild(screen);
			dlg.appendChild(container);
			document.body.appendChild(dlg); */
			
			var dlg=showDialog("noticeRegPartial",".btn-save",
					function(){
						//alert("hehehe");
						
						//데이터 수집
						var title=dlg.querySelector("input[name=title]").value;
						//alert(title);
						var content=dlg.querySelector("#txtContent").value;
						//alert(content);
						
						var data="title="+title+"&content="+content;
						
						/* ------------------------------------------ */
						var request;
						if(window.ActiveXObject)
							request = new ActiveXObject("Microsoft.XMLHTTP"); 
						else if(window.XMLHttpRequest)
							request = new XMLHttpRequest();
					
						request.onreadystatechange=function(){
							
							if(request.readyState==4 && request.status==200)   // operation is complete
							{
								if(request.responseText=="ok")
									alert("성공");
								
								//화면 갱신
								//방법1
								document.write(request.responseText);
								/* //방법2
								var page=event.target.innerText;
								var request;
									
								if(window.ActiveXObject)
									request = new ActiveXObject("Microsoft.XMLHTTP"); 
								else if(window.XMLHttpRequest)
									request = new XMLHttpRequest();
								
								//비동기식으로 데이터 요청할 경우, 빈데이터가 오는걸 방지하기 위한 로직;데이터가 온 다음에 로직이 처리되도록 하기 위함
								request.onreadystatechange=function(){
									
									if(request.readyState==4)   // operation is complete
									{
										//tbody의 안쪽 방을 비우기
										var tbody=document.querySelector("#notices tbody");
										
										tbody.innerHTML=request.responseText;
										
										//alert(request.responseText);
									}
								};
								
								request.open("GET","noticePartial?p="+"1",true);  //false면 동기, true면 비동기식
								//page++;
								request.send(null); */ 
							}
						};
						
						request.open("POST","noticeReg",true);
						//open과 send 사이에 집어넣어야 함
						request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
						request.setRequestHeader("Content-length", data.length);
						request.setRequestHeader("Connection", "close");
						
						request.send(data);
						
						/* ------------------------------------------ */
						
						//데이터 전송
						
						
						
						closeDialog(dlg);
						
						return false;
			});
			
			//-----------AJAX로 폼 추가하기--------------------------------			
			/* var request;
			if(window.ActiveXObject)
				request = new ActiveXObject("Microsoft.XMLHTTP"); 
			else if(window.XMLHttpRequest)
				request = new XMLHttpRequest();
			
			request.onreadystatechange=function(){
				
				if(request.readyState==4)   // operation is complete
				{
					//var tbody=document.querySelector("#notices tbody");
					container.innerHTML=request.responseText;
					var btnSave=container.querySelector(".btn-save");
					btnSave.onclick=function(){
						alert("test");
						return false;
					}
				}
			};
			
			request.open("GET","noticeRegPartial",true);  //false면 동기, true면 비동기식
			request.send(null);  */
			
			return false;
		};
		
		
		/* ######### pager 관련 스크립트 ################### */
		//--------------AJAX GET METHOD
		var numClick=function(event){
			
			/* -------------번호 선택 스타일 변경------------- */
			var ul=event.target.parentNode.parentNode;//.className="strong";
			var as=ul.querySelectorAll("li a")
			for(var i=0;i<as.length;i++)
				as[i].className="";
			
			event.target.className="strong";
			
			/* -------------선택된 번호의 게시글 변경------------- */
			var page=event.target.innerText;
			var request;
				
			if(window.ActiveXObject)
				request = new ActiveXObject("Microsoft.XMLHTTP"); 
			else if(window.XMLHttpRequest)
				request = new XMLHttpRequest();
			
			//비동기식으로 데이터 요청할 경우, 빈데이터가 오는걸 방지하기 위한 로직;데이터가 온 다음에 로직이 처리되도록 하기 위함
			request.onreadystatechange=function(){
				
				if(request.readyState==4)   // operation is complete
				{
					//tbody의 안쪽 방을 비우기
					var tbody=document.querySelector("#notices tbody");
					//tbody.innerHTML=request.responseText;
					
					/* #####애니메이션을 위한 예제##### */
					
					//클릭한 게시글을 가져와서
					tbody.innerHTML+=request.responseText;
					
					//슬라이드를 위한 애니메이션
					//var table=document.querySelector("#notices");
										
					//이부분은 미리 설정되어 있어야 할 정적인 스타일
					/* tbody.style.display="block";
					tbody.style.position="relative"; 
					*/
					var trs=tbody.querySelectorAll("tr");
					for(var i=0;i<trs.length;i++)
						trs[i].style.position="absolute";
					
					//table.style.height=table.clientHeight+"px";
					//trs[1].style.top="-100px";
					
					
					
					
					//위로 슬라이드
					var top=0;
					var topid=setInterval(function(){
						//애니메이션을 위한 프레임 연산 
						top-=1;
						for(var i=0;i<20;i++){
							trs[i].style.top=top+(i*31)+"px";
							
							console.log("top: "+top);
							//fade in을 위한 애니메이션 
							if(top<=-100)
							{
								clearInterval(topid);
							}
						}
					},10);
					
					/* //fade out을 위한 애니메이션 
					var v=1;
					var id=setInterval(function(){
						//애니메이션을 위한 프레임 연산 
						v-=0.01;
						tbody.style.opacity=v;
						//fade in을 위한 애니메이션 
						if(v<=0)
						{
							clearInterval(id);
							tbody.innerHTML=request.responseText;
							var v1=0;
							var id1=setInterval(function(){
								//애니메이션을 위한 프레임 연산 
								v1+=0.01;
								tbody.style.opacity=v1;
								if(v1>=1)
									clearInterval(id1);
							}, 10)
						}
					}, 10) */
					
				}
			};
			
			request.open("GET","noticePartial?pg="+page+"&f="+param.f+"&q="+param.q, true);  //false면 동기, true면 비동기식
			page++;
			request.send(null); 
			
			return false;  // 이 이벤트 핸들러와 연결된 페이지 로드가  되지 않게 하기 위함
		}
		
		var nums=document.querySelectorAll("#pager-wrapper ul a");
		for(var i=0;i<nums.length;i++)
		{
			nums[i].onclick=numClick;
		}
		
		var notices=document.getElementById("notices");
		
		var btnClone=document.querySelector("input[value='행복제']");
		btnClone.onclick=function(){
			
			var datas=[
				          {code:1, title:'<b>자바스크립트란?</b>', writer:'newlec'},
				          {code:2, title:'DOM이란?', writer:'newlec'},
				          {code:3, title:'Ajax를 이용하는 방법은?', writer:'newlec'}
			          ];
			
			var template=document.querySelector("#row-template");
			
			for(var i in datas)
			{
				var clone=template.cloneNode(true);
				clone.className="";  
				//클론한 틀에 데이터를 심기
				dataBind(clone, datas[i]);
				
				notices.querySelector("tbody").appendChild(clone);
			}
		};
		
		//전역변수로
		var img=null;
		var page=0;
		
		var btnAdd=document.querySelector("input[value='행추가']");
		btnAdd.onclick=function(){
			
			if(document.querySelector("#loadingImg") !=null || 
					document.querySelector("#loadingImg") !=undefined/* img!=undefined || img!=null */)
			{
				alert("현재 처리중입니다.");
				img=null;  
				return;
			}
			
			var request;
			
			if(window.ActiveXObject)
				request = new ActiveXObject("Microsoft.XMLHTTP"); 
			else if(window.XMLHttpRequest)
				request = new XMLHttpRequest();
			
			//비동기식으로 데이터 요청할 경우, 빈데이터가 오는걸 방지하기 위한 로직;데이터가 온 다음에 로직이 처리되도록 하기 위함
			request.onreadystatechange=function(){
				if(request.readyState==4)   // operation is complete
				{
					datas=eval(request.responseText);   // 전역변수 설정
					var template=document.querySelector("#notice-row");
					
					for(var i in datas)
					{
						var clone=document.importNode(template.content, true); // 틀만 복사
						dataBind(clone, datas[i]);
						notices.querySelector("tbody").appendChild(clone); 
					}
					
					var temp=document.querySelector("#loadingImg");
					document.body.removeChild(temp);
					//document.body.removeChild(img);  //데이터받기가 완료되었으면 로딩이미지를 지운다
				}
			};
			
			page++;
			request.open("GET","noticeJSON?p="+page,true);  //false면 동기, true면 비동기식
			request.send();  // 데이터 요청..동기식으로 하면 여기서 freezing됨, 근데 비동기식으로 하면 eval할때 빈데이터가 와서 에러...그럼 어떻게??
				
			//var img=null;
			img=document.createElement("img");
			
			//url창기준으로 위치를 설정할것
			img.src="../content/images/ajax-loader.gif";
			img.setAttribute("id", "loadingImg");  //위에서 선택해서 삭제할려구 필요
			img.style.position="absolute";
			img.style.left="400px";
			img.style.top="200px";
			document.body.appendChild(img);					
			
			
			/* datas=eval(request.responseText);   // 전역변수 설정
			var template=document.querySelector("#notice-row");
			
			for(var i in datas)
			{
				
				var clone=document.importNode(template.content, true); // 틀만 복사
			
				dataBind(clone, datas[i]);
				notices.querySelector("tbody").appendChild(clone); 
			} */
		};
	};
</script>


<h2>공지사항</h2>

	<h3 class="hidden">방문페이지 로그</h3>
	<ul id="breadscrumb" class="block_hlist clear">
		<li>HOME</li>
		<li>
			고객센터
		</li>
		<li>
			공지사항목록
		</li>
	</ul>
	<h3 class="hidden">공지사항 목록</h3>
	<form id="content-searchform" class="article-search-form" action="notice" method="get">
		<fieldset>
			<legend class="hidden">
				목록 검색 폼
			</legend>
			<input type="hidden" name="pg" value="" />
			<label for="f"
			class="hidden">검색필드</label>
			<select name="f">
			
				<option value="TITLE" <c:if test="${param.f=='TITLE'}">selected="selected"</c:if>>제목</option>
				<option value="CONTENT" <c:if test="${param.f=='CONTENT'}">selected="selected"</c:if>>내용</option>
			</select>
			<label class="hidden" for="q">검색어</label>
			<input type="text"
			name="q" value="${param.q}" />
			<input type="submit" value="검색" />
		</fieldset>
	</form>
	<table id="notices" class="article-list margin-small">
		<caption class="hidden">
			공지사항
		</caption>
		<thead>
			<tr>
				<th class="seq">번호</th>
				<th class="title">제목</th>
				<th class="writer">작성자</th>
				<th class="regdate">작성일</th>
				<th class="hit">조회수</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="n" items="${list}">
		<!-- n은 페이지 컨텍스트에 담겨져 있는 key name이지, 지역변수가 아님 만약 코드블록을 이용해서 사용하려면 pageContext를 호출해서 써야 되는거임 -->
			<%-- <tr id="row-template" class="hidden">
				<td class="seq"></td>
				<td class="title"><a href="noticeDetail?c="></a></td>
				<td class="writer"></td>
				<td class="regdate"><fmt:formatDate pattern="yyyy-MM-dd" value=""/></td>
				<td class="hit"></td>
			</tr> --%>
			<tr>
				<td class="seq">${n.code}</td>
				<td class="title"><a href="noticeDetail?c=${n.code}">${n.title}</a></td>
				<td class="writer">${n.writer}</td>
				<td class="regdate"><fmt:formatDate pattern="yyyy-MM-dd" value="${n.regDate}"/></td>
				<td class="hit">${n.hit}</td>
			</tr>
		</c:forEach>
		<%-- <% for(Notice n: list) { %>
			<tr>
				<td class="seq"><%=n.getCode() %></td>
				<td class="title"><a href="noticeDetail.jsp?c=<%=n.getCode() %>"><%= n.getTitle() %></a></td>
				<td class="writer"><%=n.getWriter() %></td>
				<td class="regdate"><%=n.getRegDate() %></td>
				<td class="hit"><%=n.getHit() %></td>
			</tr>
		<% } %> --%>
		</tbody>
	</table>
	
	<template id="notice-row">
		<tr>
			<td class="seq"></td>
			<td class="title"><a href="noticeDetail?c="></a></td>
			<td class="writer"></td>
			<td class="regdate"><%-- <fmt:formatDate pattern="yyyy-MM-dd" value=""/> --%></td>
			<td class="hit"></td>
		</tr>
	</template>
	
	
	<p class="article-comment margin-small">
		<input type="button" value="행복제"/>
		<input type="button" value="행추가"/>
		<input type="button" value="데이터요청"/>
		<%-- <security:authorize ifAnyGranted="ROLE_ADMIN">	 --%>
			<a id="btn-write" class="btn-write button" href="noticeReg">글쓰기</a>
		<%-- </security:authorize> --%>
	</p>
	
	
	<!-- 현재페이지, 라스트페이지등 -->
	
	
	<p id="cur-page" class="margin-small">
		<span class="strong">${pg}</span> /
			 ${lastNum}	page
	</p>
	
	
	<div id="pager-wrapper" class="margin-small">
		<!-- 페이저가 포함됟 부분 -->
		<%-- <my:pager lastNum="12"></my:pager> --%>
		<my:pager></my:pager>
	</div>
	
	<div><input type="file"/>
		<span id="open-box" style="background:purple; color:white;">파일열기</span>
	</div>
	