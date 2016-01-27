<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<form action="noticeReg" method="post" >
	<div id="notice-article-detail" class="article-detail margin-large" >
		<!-- <form action="noticeRegProc.jsp" method="post"> -->
		<dl class="article-detail-row">
			<dt class="article-detail-title">
				제목
			</dt>
			<dd class="article-detail-data">
				&nbsp;<input name="title"/>
			</dd>
		</dl>				
								
		<dl class="article-detail-row">
			<dt class="article-detail-title">
				첨부파일
			</dt>
			<dd class="article-detail-data">
				&nbsp;<input type="file" id="txtFile" name="file" />
			</dd>
		</dl>

		<div class="article-content" >
			<textarea id="txtContent" class="txtContent" name="content"></textarea>
		</div>
		<!-- </form> -->
	</div>
	<p class="article-comment margin-small">						
		<input class="btn-save button" type="submit" value="저장" />
		<a class="btn-cancel button" href="notice">취소</a>						
	</p>							
</form>	
