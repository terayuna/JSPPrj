package com.newlecture.webprj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.webprj.vo.Member;

public class MemberDao {
	
	public List<Member> getMembers() throws SQLException {
		return getMembers(1);
	}

	public List<Member> getMembers(int page) throws SQLException {
		
				List<Member> list = new ArrayList<Member>();

				int start = 1+(page-1)*10;
				int end = page*10;
				
				
				String sql = " SELECT * FROM(SELECT ROW_NUMBER() OVER (ORDER BY REGDATE DESC) NUM, MEMBERS.*FROM MEMBERS) A "
						+ "WHERE NUM BETWEEN "+start + " AND " + end;
				
				String url = "jdbc:sqlserver://211.238.142.251:1433;databaseName=edudb";

				Connection con = DriverManager.getConnection(url, "edu", "class2d");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);

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

}
