/**
 * 
 */


var wsocket;

var uid="chunghee";
var btnConn=document.querySelector("#btn-conn");
var btnSend=document.querySelector("#chat-input-panel .btn");
var outputList=document.querySelector("#chat-output ul");  // 출력부
var inputBox=document.querySelector("#chat-input-panel textarea"); //입력부
var chatOutput=document.querySelector("#chat-output");


function printMessage(userName,msg){
	var template=document.querySelector("#chat-template");	
	var clone=document.importNode(template.content, true); // 틀만 복사
	//clone한 틀에 데이터를 심자
	var userNameBox=clone.querySelector("li > div > div:first-child");
	var msgBox=clone.querySelector("li > div > div:last-child");
	userNameBox.innerText=userName;
	msgBox.innerText=msg;
	
	outputList.appendChild(clone);
	chatOutput.scrollTop=chatOutput.scrollHeight;
	//alert(inputBox.value);
}

function accept(event){
	
	if(wsocket==undefined)
	{
		//방번호를 담아서 접속요청하거나 send할때 방번호 넘김
		wsocket=new WebSocket("ws://211.238.142.106/JSPPrj1/content/chatserver"); // 브라우저가 제공하는 클라이언트 소켓
		
		wsocket.onopen=sockOpen;
		wsocket.onclose=sockClose;
		wsocket.onmessage=sockMessage;
	}
	
	function sockOpen(){
		alert("접속되었습니다.");
	}
	function sockClose(){
		alert("접속이 끊겼습니다.");
	}
	function sockMessage(event){
		//alert(event.data);
		var data=JSON.parse(event.data) // 서버측으로부터 받은 string->object로 parsing
		switch(data.type)
		{
			case "chat":
				printMessage(data.uid, data.msg);
				break;
			case "draw":
				var img=document.querySelector("img[data-id='"+data.id+"']");
				img.style.left=data.x;
				img.style.top=data.y;
				break;
		}
	}
	
};



function conn()
{
	alert("test");
	accept();
}

alert(btnSend);

btnSend.onclick=function(event){
	
	alert("zzzzz");
	//var userName="chunghee";
	
	/*var msg=inputBox.value;
	var data={type:"chat", uid:uid, msg:msg};
	data=JSON.stringify(data);    // object->string으로 parsing
	
	wsocket.send(data);
	inputBox.value="";*/	
};

//---------------엔터키 개행처리 등 기타------------------
/*var signal=false; // 개행처리인지 메시지 전송인지를 확인하기 위한 변수
inputBox.onkeyup=function(e)
{
	if(e.keyCode==13 && signal) // signal이 true이면 아무일도 없음
	{
		signal=false;
		return;
	}
	if(e.keyCode==13)  // 그 외에는 입력부분 문자열 리셋
    {
		inputBox.value="";		
    }
}
	
inputBox.onkeydown=function(e)  // onkeydown, onkeypress(문자와 관련된거)
{
	if(e.keyCode==13 && e.altKey)   // 개행처리
	{
		inputBox.value+="\n";
		signal=true; 
		return;
	}
	if(e.keyCode==13)  //  메시지 전송 이벤트 트리거
    {
    	var event=document.createEvent("MouseEvent");
    	event.initEvent("click",true,true);	
    	btnSend.dispatchEvent(event);
    }
}	*/



window.addEventListener("load",function(){
	
	var curImg=null;
	var board=document.querySelector("#board");
	
	//시작점
	var pos={x:0, y:0};	
	
	var dragging=false;
	
	board.onmousedown=function(event){		
		curImg=event.target;
		var rect=curImg.getBoundingClientRect();
		var rect1=board.getBoundingClientRect();
		
		pos.x=event.clientX-(rect.left-rect1.left);
		pos.y=event.clientY-(rect.top-rect1.top);
		
		dragging=true;
	};
	
	board.onmouseup=function(event){
		dragging=false;
		
		var x=event.clientX-pos.x+"px";
		var y=event.clientY-pos.y+"px";
		var id=event.target.dataset.id;
		var data={type:"draw", id:id, x:x, y:y};
		data=JSON.stringify(data);    // object->string으로 parsing
		//alert(data);		
		
		wsocket.send(data);
	};
	
	board.onmousemove=function(event){
		
		if(curImg!=null && dragging)
		{
			console.log(event.x);
			curImg.style.left=event.clientX-pos.x+"px";
			curImg.style.top=event.clientY-pos.y+"px";	
			
			event.preventDefault();	
		}
	};
});




window.addEventListener("load", function(){
	
	/*//현재 페이지를 구성하는 객체 또는 변수 선언	
	var uid="chunghee";
	
	var btnConn=document.querySelector("#btn-conn");
	var btnSend=document.querySelector("#chat-input-panel .btn");
	var outputList=document.querySelector("#chat-output ul");  // 출력부
	var inputBox=document.querySelector("#chat-input-panel textarea"); //입력부
	var chatOutput=document.querySelector("#chat-output");
	
	//현재 페이지에서 사용할 서비스 함수들
	function printMessage(userName,msg){
		var template=document.querySelector("#chat-template");	
		var clone=document.importNode(template.content, true); // 틀만 복사
		//clone한 틀에 데이터를 심자
		var userNameBox=clone.querySelector("li > div > div:first-child");
		var msgBox=clone.querySelector("li > div > div:last-child");
		userNameBox.innerText=userName;
		msgBox.innerText=msg;
		
		outputList.appendChild(clone);
		chatOutput.scrollTop=chatOutput.scrollHeight;
		//alert(inputBox.value);
	}
	
	//현재 페이지에서 사용하는 이벤트 처리 함수들
	btnConn.onclick=function(event){
		//alert(wsocket);
		if(wsocket==undefined)
		{
			wsocket=new WebSocket("ws://211.238.142.106/JSPPrj1/content/chatserver"); // 브라우저가 제공하는 클라이언트 소켓
			
			wsocket.onopen=sockOpen;
			wsocket.onclose=sockClose;
			wsocket.onmessage=sockMessage;
		}
		//alert(wsocket);
	};
	
	function sockOpen(){
		alert("접속되었습니다.");
	}
	function sockClose(){
		alert("접속이 끊겼습니다.");
	}
	function sockMessage(event){
		//alert(event.data);
		var data=JSON.parse(event.data) // 서버측으로부터 받은 string->object로 parsing
		switch(data.type)
		{
			case "chat":
				printMessage(data.uid, data.msg);
				break;
			case "draw":
				var img=document.querySelector("img[data-id='"+data.id+"']");
				img.style.left=data.x;
				img.style.top=data.y;
				break;
		}
	}
	
	btnSend.onclick=function(event){
		
		//var userName="chunghee";
		var msg=inputBox.value;
		var data={type:"chat", uid:uid, msg:msg};
		data=JSON.stringify(data);    // object->string으로 parsing
		
		wsocket.send(data);
		inputBox.value="";
		
	};*/
	
	/*var signal=false; // 개행처리인지 메시지 전송인지를 확인하기 위한 변수
	inputBox.onkeyup=function(e)
	{
		if(e.keyCode==13 && signal) // signal이 true이면 아무일도 없음
		{
			signal=false;
			return;
		}
		if(e.keyCode==13)  // 그 외에는 입력부분 문자열 리셋
	    {
			inputBox.value="";		
	    }
	}
		
	inputBox.onkeydown=function(e)  // onkeydown, onkeypress(문자와 관련된거)
	{
		if(e.keyCode==13 && e.altKey)   // 개행처리
		{
			inputBox.value+="\n";
			signal=true; 
			return;
		}
		if(e.keyCode==13)  //  메시지 전송 이벤트 트리거
	    {
	    	var event=document.createEvent("MouseEvent");
	    	event.initEvent("click",true,true);	
	    	btnSend.dispatchEvent(event);
	    }
	}	*/
});