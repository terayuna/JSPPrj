<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="../aaa/Add" method="post">
		<ul>
			<li><label for="x">X:</label><input name="x"/></li>
			<li><label for="y">Y:</label><input name="y"/></li>
		</ul>
		<p><input type="submit" value="덧셈"/></p>
	</form>
</body>
</html>


<!-- 원래 html파일이였다가 jsp로 바꿈 -->

<!-- html을 jsp로 바꾸면 한글이 깨짐: file->property->text file encoding->UTF-8 -->

<!-- 실행 - 톰캣서버로 배포 - 톰캣 실행  - 사용자가 get요청 -->