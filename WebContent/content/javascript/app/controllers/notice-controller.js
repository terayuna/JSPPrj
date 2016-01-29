﻿var noticeModule = angular.module("notice", []);

noticeModule.controller('notice-controller', function ($scope) {
    	
	var test="hello world";
	$scope.aa=test;
	var list = [
        { "id": "1", "title": "새로운 서비스가 시작됩니다.", "writer": "newlec", "regdate": "2016-02-16", "hit": 12, "img":"icon-recommend.png" },
        { "id": "2", "title": "새로운 강의가 업로드 되었습니다.", "writer": "newlec", "regdate": "2016-02-22", "hit": 72, "img":"icon-recommend.png" },
        { "id": "3", "title": "JSP 강의가 업데이트 되었습니다.", "writer": "newlec", "regdate": "2016-02-26", "hit": 245, "img":"icon-recommend.png" },
        { "id": "4", "title": "Spring 강의가 새로 추가 되었습니다.", "writer": "newlec", "regdate": "2016-02-27", "hit": 512, "img":"icon-recommend.png" },
        { "id": "5", "title": "Javascript 관련 강좌가 추가될 예정입니다.", "writer": "newlec", "regdate": "2016-02-29", "hit": 24, "img":"icon-recommend.png" },
        { "id": "6", "title": "jQuery 강좌가 새로 업로드 되었습니다.", "writer": "newlec", "regdate": "2016-03-01", "hit": 123, "img":"icon-recommend.png" },
        { "id": "7", "title": "AngularJS 강좌가 추가 되었습니다.", "writer": "newlec", "regdate": "2016-03-03", "hit": 112, "img":"icon-recommend.png" },
        { "id": "8", "title": "이벤트가 진행중입니다.", "writer": "newlec", "regdate": "2016-03-10", "hit": 1, "img":"icon-recommend.png" },
        { "id": "9", "title": "NodeJS 강의가 진행중입니다.", "writer": "newlec", "regdate": "2016-03-17", "hit": 0, "img":"icon-recommend.png" },
        { "id": "10", "title": "Linux 강좌를 확인해 보세요.", "writer": "newlec", "regdate": "2016-03-20", "hit": 0, "img":"icon-recommend.png" },
        { "id": "11", "title": "Mean 스택 프로젝트 강좌를 확인해 보세요.", "writer": "newlec", "regdate": "2016-03-30", "hit": 10, "img":"icon-recommend.png" },
        { "id": "12", "title": "훈장님 서비스가 시작됩니다.", "writer": "newlec", "regdate": "2016-04-06", "hit": 3, "img":"icon-recommend.png" },
        { "id": "13", "title": "훈장님의 지도를 받아보세요.", "writer": "newlec", "regdate": "2016-04-10", "hit": 2, "img":"icon-recommend.png" }
    ];
	
	//VM초기설정
	$scope.list = list;
	$scope.noticeRegVisible = false;
	$scope.title = "";
	$scope.content="";
	
	//이벤트 설정
	$scope.toggleNoticeReg = function(){
		$scope.noticeRegVisible = !$scope.noticeRegVisible;
	};
	$scope.btnWriteClick = function(){
		var notice = { "id": "13", "title": $scope.title, "writer": "newlec", "regdate": "2016-04-10", "hit": 2, "img":"icon-recommend.png" }
	    $scope.list.splice(0, 0, notice);
		//$scope.list.push(notice);
		event.preventDefault();
		
	};
});