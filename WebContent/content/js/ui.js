/**
 * 작성일 : 15/12/30
 * 작성자 : chung
 */

var showDialog=function(url,btnSelector,btnHandler){
	
	var dlg=document.createElement("div");
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
	
	var closeButton=document.createElement("input");
	closeButton.type="button";
	closeButton.value="X";
	closeButton.style.width="20px";
	closeButton.style.height="20px";
	closeButton.style.position="fixed";
	closeButton.style.left=parseInt(container.style.left)+parseInt(container.style.width)-20+"px";
	closeButton.style.top=parseInt(container.style.top)+"px";
	closeButton.style.zIndex=1;
	
	closeButton.onclick=function(){
		closeDialog(dlg);
	};
	
	dlg.appendChild(closeButton);
	dlg.appendChild(screen);
	dlg.appendChild(container);
	
	document.body.appendChild(dlg);
	
	//-----------AJAX로 폼 추가하기--------------------------------	
	var request;
	if(window.ActiveXObject)
		request = new ActiveXObject("Microsoft.XMLHTTP"); 
	else if(window.XMLHttpRequest)
		request = new XMLHttpRequest();
	
	request.onreadystatechange=function(){
		
		if(request.readyState==4)   // operation is complete
		{
			container.innerHTML=request.responseText;
			var btnSave=container.querySelector(btnSelector);  //사용자가 선택한 OK버튼
			btnSave.onclick=btnHandler  // 사용자가 넘긴 버튼 핸들러
			  /*function(){
				alert("test");
				return false;
			}*/
		}
	};
	
	request.open("GET",url,true);  //false면 동기, true면 비동기식
	request.send(null); 
	
	return dlg;
};

var closeDialog=function(dlg){
	document.body.removeChild(dlg);
};