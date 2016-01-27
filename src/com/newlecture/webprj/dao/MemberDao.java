package com.newlecture.webprj.dao;

import java.sql.SQLException;
import java.util.List;

import com.newlecture.webprj.vo.Member;

public interface MemberDao {

	public List<Member> getMembers() throws SQLException;
	public List<Member> getMembers(int page) throws SQLException;
	public List<Member> getMembers(int page, String filed, String query) throws SQLException;
	
	public Member getMember(String mid) throws SQLException;
	
	public int update(Member member) throws SQLException;
	public int delete(String mid) throws SQLException;
	public int insert(Member member) throws SQLException;
	
		
	
	
}
