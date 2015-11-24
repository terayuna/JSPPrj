package com.newlecture.webprj.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.newlecture.webprj.dao.NoticeDao;
import com.newlecture.webprj.vo.Notice;

public class MyBatisNoticeDao implements NoticeDao{
	
	SqlSessionFactory ssf = NewlecSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public List<Notice> getNotices(int page, String field, String query) {
		SqlSession session = ssf.openSession();
		NoticeDao dao = session.getMapper(NoticeDao.class);
		List<Notice>list = dao.getNotices(page,field,query);
		session.close();
		return list;
	}
	
	

}
