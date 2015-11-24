
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
		
		System.out.println("�˻����:" + list.size());
		
		for(Member m : list)
		{
			System.out.printf("mid : %s, name: %s, pwd: %s, regDate : %s\n", 
					m.getMid(),m.getName(),m.getPwd(), m.getRegdate());
		}
		
		System.out.printf("%d\n", update);
		//session.close();  //session ����� �� ���������� ����
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

//api - �÷���(����)�� �������ִ� ���̺귯��
//�����ӿ�ũ - ��ü�������� �������� ���� ������ ���̺귯��
//mybatis�� ���Ǽ��� �ִ� �����ӿ�ũ(�ɼ�-�־�ǰ� �����), ������ api(�� �ʿ��� ����)�� �ƴ�
