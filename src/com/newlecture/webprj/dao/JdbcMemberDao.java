package com.newlecture.webprj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.webprj.vo.Member;

public class JdbcMemberDao implements MemberDao {
	
	public List<Member> getMembers() throws SQLException {
		return getMembers(1, "Mid","");
	}
	
	public List<Member> getMembers(int page) throws SQLException {
		return getMembers(page, "Mid","");
		
	}

	public List<Member> getMembers(int page, String filed, String query) throws SQLException {
		
				List<Member> list = new ArrayList<Member>();

				int start = 1+(page-1)*10;
				int end = page*10;
			//	WHERE "+filed+" LIKE ? AND 
				
				String sql = " SELECT * FROM(SELECT ROW_NUMBER() OVER (ORDER BY REGDATE DESC) NUM, MEMBERS.*FROM MEMBERS WHERE "+filed+" LIKE ?) A "
						+ "WHERE NUM BETWEEN ? AND ?";
				
				String url = "jdbc:sqlserver://211.238.142.251:1433;databaseName=edudb";

				Connection con = DriverManager.getConnection(url, "edu", "class2d");
				//Statement st = con.createStatement();
				//ResultSet rs = st.executeQuery(sql);
				
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, "%"+query+"%");
				st.setInt(2, start);
				st.setInt(3, end);
				
				ResultSet rs = st.executeQuery();
				

				Member member = null;
				
				while(rs.next())
				{
				member = new Member();
			
				member.setMid(rs.getString("mid"));
				member.setRegdate(rs.getDate("regdate"));
				
				list.add(member);
				}
				
				
				rs.close();
				st.close();
				con.close();
			
	
		return list;
	}

	@Override
	public int update(Member member) throws SQLException {
		
		String sql = "UPDATE Members SET Name = ?, Pwd = ?";
		String url = "jdbc:sqlserver://211.238.142.251:1433;databaseName=edudb";
		Connection con = DriverManager.getConnection(url, "edu", "class2d");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, member.getPwd());
		st.setString(2, member.getMid());
		st.setString(3, member.getName());
		
		int count = st.executeUpdate();
		st.close();
		con.close();
		return count;
	}

	@Override
	public int delete(String mid) throws SQLException {
		String sql = "DELETE Members WHERE Mid = ?";
		String url = "jdbc:sqlserver://211.238.142.251:1433;databaseName=edudb";
		Connection con = DriverManager.getConnection(url, "edu", "class2d");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, mid);
		
		int count = st.executeUpdate();
		st.close();
		con.close();
		return count;
	}

	@Override
	public int insert(Member member) throws SQLException {
		String sql = "INSERT INTO Members(Mid, Name, Pwd) VALUES(?,?,?)";
		String url = "jdbc:sqlserver://211.238.142.251:1433;databaseName=edudb";
		Connection con = DriverManager.getConnection(url, "edu", "class2d");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, member.getMid());
		st.setString(2, member.getName());
		st.setString(3, member.getPwd());

		int count = st.executeUpdate();
		st.close();
		con.close();
		return count;
	}

}

//JDBC-데이터베이스 연결하기위한 라이브러리
//1.connection
//2.statement(쿼리문을 실행할 수 있는 것)
//3.resultset(next함수가 있어서 다음 행을 가져올 수 있음) - select만 필요함