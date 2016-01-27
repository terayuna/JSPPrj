package com.newlecture.webprj.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.webprj.dao.NoticeDao;
import com.newlecture.webprj.vo.Notice;

public class MyBatisNoticeDao implements NoticeDao{
	
	//SqlSessionFactory ssf = NewlecSqlSessionFactoryBuilder.getSqlSessionFactory();

	
	@Autowired
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Notice> getNotices(int page, String field, String query) {
		//SqlSession session = ssf.openSession();
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		List<Notice>list = dao.getNotices(page,field,query);
		//sqlSession.close();
		return list;
	}

	@Override
	public Notice getNotice(String code) {
		//SqlSession session = ssf.openSession();
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		Notice notice = dao.getNotice(code);
		//sqlSession.close();
		return notice;
	}

	@Override
	public int insert(Notice notice) {
		// TODO Auto-generated method stub
		//SqlSession session = ssf.openSession();
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		int a=0;
		a=dao.insert(notice);
		/*try{
			a=dao.insert(notice);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			sqlSession.rollback();
			System.out.println("¸ô¶ó~~");
		}
		sqlSession.commit();
		sqlSession.close();*/
		return a;
	}

	@Override
	public String getLastCode() {
		// TODO Auto-generated method stub
		//SqlSession session = ssf.openSession();
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		String lastCode = dao.getLastCode();
		//sqlSession.close();
		return lastCode;
	}

	@Override
	public Notice getPrevNotice(String code) {
		// TODO Auto-generated method stub
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		Notice notice = dao.getPrevNotice(code);
		//sqlSession.close();
		return notice;
	}

	@Override
	public Notice getNextNotice(String code) {
		// TODO Auto-generated method stub
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		Notice notice = dao.getNextNotice(code);
		//sqlSession.close();
		return notice;
	}

	@Override
	public int delete(String code) {
		// TODO Auto-generated method stub
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		return dao.delete(code);
	}

	@Override
	public int getNoticeCount(String field, String query) {
		// TODO Auto-generated method stub
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		return dao.getNoticeCount(field,query);
	}
}
