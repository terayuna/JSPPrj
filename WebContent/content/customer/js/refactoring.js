/**
 * 
 */


//슬라이드를 위한 애니메이션

function slideUp(target, to){
	//var table=document.querySelector("#notices");
						
	//이부분은 미리 설정되어 있어야 할 정적인 스타일
	/* tbody.style.display="block";
	tbody.style.position="relative"; 
	*/
	
	if(typeof target=="string")
		var target=document.querySelector(target);
	
	if(to==undefined)
		to=-target.clientHeight;
		
	var trs=target.querySelectorAll("tr");
	for(var i=0;i<trs.length;i++)
		trs[i].style.position="absolute";
	
	//table.style.height=table.clientHeight+"px";
	//trs[1].style.top="-100px";
		
	//위로 슬라이드
	var top=0;
	var topid=setInterval(function(){
		//애니메이션을 위한 프레임 연산 
		top-=2;
		for(var i=0;i<20;i++){
			trs[i].style.top=top+(i*31)+"px";
			
			console.log("top: "+top);
			//fade in을 위한 애니메이션 
			if(top<=to)
			{
				clearInterval(topid);
			}
		}
	},10)
}