//빈문자열, null, undefined 모두 false값으로 취급됨
/* var a = "a";
 var a1 = "bss";
 var a2 = '오잉';
 
 var b = a && a1 && a2;
 alert(b);
 if(b)
	 alert("ok");
 else 
	 alert("sorry");*/

var fileuploadModule = angular.module("fileupload", ["ngAnimate"]);

fileuploadModule.controller('fileupload-controller', function ($scope, $http) {
   $scope.dropboxClass="";
   $scope.dropboxText="업로드할 파일을 여기에 드래그 하세요";
   
   //angular에서 드래그와 관련해서 제공하는 이벤트 디렉티브가 없으므로 프레젠터를 이용하는수밖에
   var dropbox=document.querySelector(".dropbox");
   dropbox.addEventListener("dragenter", function(event){
	   event.stopPropagation();
	   event.preventDefault();
      $scope.$apply(function(){
         $scope.dropboxText="드랍오케~~~!!!!";
      })
      
      //dropbox.innerText="ok~~!!";
   }, false) // 세번째인자는 버블링으로 이벤트 캡쳐링 됨..기본값이 false이므로 생략 가능
   
   dropbox.addEventListener("dragleave", function(event){
	   event.stopPropagation();
	   event.preventDefault();
      $scope.$apply(function(){
         $scope.dropboxText="업로드할 파일을 여기에 드래그 하세요";
         $scope.dropboxClass = "";
      })
      
      //dropbox.innerText="ok~~!!";
   });
   
    dropbox.addEventListener("dragover", function(event){
    	 event.stopPropagation();
 	   	event.preventDefault();
    	var valid = event.dataTransfer 
    				&& event.dataTransfer.types 
    				&& event.dataTransfer.types.indexOf("Files")>=0;
    	
    	$scope.$apply(function(){
    	 //$scope.dropboxText = event.dataTransfer.types;
        $scope.dropboxText= valid? "드롭하세요!" : "유효한 파일이 아닙니다	";
        $scope.dropboxClass = valid? "valid" : "invalid";
      });
      
      //dropbox.innerText="ok~~!!";
   });
    
    dropbox.addEventListener("drop", function(event){
    	event.stopPropagation();
 	   	event.preventDefault();
 	   	
 	   	$scope.$apply(function(){
 	   	  $scope.files = event.dataTransfer.files;
 	 	  /* 	for(var i=0; i<files.length; i++) 
 	 	   		alert(files[i].name);*/
 	   	});

    });
});
