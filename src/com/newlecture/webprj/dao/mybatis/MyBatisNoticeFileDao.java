package com.newlecture.webprj.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.newlecture.webprj.dao.MemberDao;
import com.newlecture.webprj.dao.NoticeDao;
import com.newlecture.webprj.dao.NoticeFileDao;
import com.newlecture.webprj.vo.Notice;
import com.newlecture.webprj.vo.NoticeFile;

public class MyBatisNoticeFileDao implements NoticeFileDao {

	SqlSessionFactory ssf = NewlecSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<NoticeFile> getNoticeFiles(String noticeCode) {
		SqlSession session = ssf.openSession();
		NoticeFileDao dao = session.getMapper(NoticeFileDao.class);
		List<NoticeFile>list = dao.getNoticeFiles(noticeCode);
		session.close();
		return list;
	}

	@Override
	public int insert(NoticeFile noticeFile) {
		// TODO Auto-generated method stub
		SqlSession session = ssf.openSession();
		NoticeFileDao dao = session.getMapper(NoticeFileDao.class);
		int count = dao.insert(noticeFile);
		session.commit();
		session.close();
		return count;
	}

}
