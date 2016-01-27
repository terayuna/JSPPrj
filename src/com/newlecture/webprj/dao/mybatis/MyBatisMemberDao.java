package com.newlecture.webprj.dao.mybatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.webprj.dao.MemberDao;
import com.newlecture.webprj.vo.Member;

public class MyBatisMemberDao implements MemberDao{
	
	//SqlSessionFactory ssf = NewlecSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Autowired
	private SqlSession session;
	
	
	@Override
	public List<Member> getMembers() throws SQLException {
		// TODO Auto-generated method stub
		return getMembers(1, "MID", "");
	}

	@Override
	public List<Member> getMembers(int page) throws SQLException {
		// TODO Auto-generated method stub
		return getMembers(page, "MID", "");
	}

	@Override
	public List<Member> getMembers(int page, String fieled, String query) throws SQLException {
		//SqlSession session = ssf.openSession();
		MemberDao dao = session.getMapper(MemberDao.class);
		List<Member>list = dao.getMembers(page,fieled,query);
		session.close();
		return list;
	}

	@Override
	public int update(Member member) throws SQLException {
		//SqlSession session = ssf.openSession();
		MemberDao dao = session.getMapper(MemberDao.class);
		int count = dao.update(member);
		session.commit();
		session.close();
		return count;
	}

	@Override
	public int delete(String mid) throws SQLException {
		//SqlSession session = ssf.openSession();
		MemberDao dao = session.getMapper(MemberDao.class);
		int count = dao.delete(mid);
		session.commit();
		session.close();
		return count;
	}

	@Override
	public int insert(Member member) throws SQLException  {
		//SqlSession session = ssf.openSession();
		MemberDao dao = session.getMapper(MemberDao.class);
		int count = dao.insert(member);
		session.commit();
		session.close();
		return count;
	}

	@Override
	public Member getMember(String mid) throws SQLException {
		// TODO Auto-generated method stub
		//SqlSession session = ssf.openSession();
		MemberDao dao = session.getMapper(MemberDao.class);
		Member member = dao.getMember(mid);
		session.close();
		return member;
	}

}
