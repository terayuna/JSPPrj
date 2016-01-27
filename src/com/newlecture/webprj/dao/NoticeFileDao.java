package com.newlecture.webprj.dao;

import java.util.List;

import com.newlecture.webprj.vo.Notice;
import com.newlecture.webprj.vo.NoticeFile;

public interface NoticeFileDao {
	public List<NoticeFile> getNoticeFiles(String noticeCode);
	public int insert(NoticeFile noticeFile);
}
