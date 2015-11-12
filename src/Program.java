import java.sql.SQLException;
import java.util.List;

import com.newlecture.webprj.dao.MemberDao;
import com.newlecture.webprj.vo.Member;

public class Program {

	public static void main(String[] args) throws SQLException {

		MemberDao dao = new MemberDao();
		List<Member>list = dao.getMembers();
		
		for(Member m : list)
		{
			System.out.printf("mid : %s, regdate : %s \n", m.getMid(), m.getRegdate() );
		}
		

	}

}
