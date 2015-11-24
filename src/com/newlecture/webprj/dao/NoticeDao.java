package com.newlecture.webprj.dao;

import java.util.List;

import com.newlecture.webprj.vo.Notice;

public interface NoticeDao {
public List<Notice> getNotices(int page, String field, String query);
}
