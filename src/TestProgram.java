
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.newlecture.webprj.dao.NoticeDao;
import com.newlecture.webprj.dao.mybatis.MyBatisNoticeDao;
import com.newlecture.webprj.vo.Notice;

public class TestProgram {

	public static void main(String[] args) throws SQLException, ParseException {

		//JdbcMemberDao dao = new JdbcMemberDao();
		
		/*SqlSessionFactory ssf = NewlecSqlSessionFactoryBuilder.getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		
		MemberDao dao = session.getMapper(MemberDao.class);
		*/
		
		
		/*Member member = new Member();
		member.setMid("sangho");
		member.setPwd("3333");
		member.setName("waytogo");
		member.setRegdate(new Date(System.currentTimeMillis()));
		
		MemberDao dao = new MyBatisMemberDao();
		//int update = dao.delete("yb");
		int update = dao.update(member);
		//int update = dao.insert(member);
		
		List<Member>list = dao.getMembers(1,"Mid","sangho");
		//List<Member>list = dao.getMembers(1,"Mid","");
		
		System.out.println("검색결과:" + list.size());
		
		for(Member m : list)
		{
			System.out.printf("mid : %s, name: %s, pwd: %s, regDate : %s\n", 
					m.getMid(),m.getName(),m.getPwd(), m.getRegdate());
		}
		
		System.out.printf("%d\n", update);
		//session.close();  //session 종료는 맨 마지막에만 가능
*/		
		
		NoticeDao dao = new MyBatisNoticeDao();
		List<Notice>list = dao.getNotices(1, "Title","");
		for(Notice n: list)
		{
		System.out.printf("title : %s, code: %s, writer: %s, hit : %d\n", 
				n.getTitle(),n.getCode(),n.getWriter(),n.getHit());
		}
	}

}  

//api - 플렛폼(도구)이 제공해주는 라이브러리
//프레임워크 - 객체지향적인 관점에서 재사용 가능한 라이브러리
//mybatis는 편의성을 주는 프레임워크(옵션-있어도되고 없어도됨), 따리서 api(꼭 필요한 도구)가 아님
