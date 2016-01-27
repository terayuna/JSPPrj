package com.newlecture.webprj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.newlecture.webprj.vo.Notice;

public interface NoticeDao {
	
	@Select("SELECT * FROM "
			+"(SELECT ROW_NUMBER() OVER (ORDER BY REGDATE DESC) NUM, "
			+"NoticeView.* FROM NoticeView WHERE ${param2} LIKE '%${param3}%') A "
			+"WHERE NUM BETWEEN 1+(#{param1}-1)*10 AND #{param1}*10")
	public List<Notice> getNotices(int page, String field, String query);
	
	@Select("SELECT * FROM NOTICES WHERE CODE = #{code}")
	public Notice getNotice(String code);
	
	@Insert("INSERT INTO NOTICES(CODE, TITLE, CONTENT, WRITER) VALUES(#{code}+1, #{title}, #{content}, #{writer})")
	@SelectKey(
			statement="SELECT MAX(CAST(CODE AS INT)) CODE FROM NOTICES",
			keyProperty="code",
			before=true,
			resultType=String.class)
	public int insert(Notice notice);
	public String getLastCode();
	public Notice getPrevNotice(String code);
	public Notice getNextNotice(String code);
	public int delete(String code);

	public int getNoticeCount(String field, String query);
}
