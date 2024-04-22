package am.client;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import am.vo.DeptVO;
import am.vo.EmpVO;
import am.vo.LocaVO;

public class Main_Ex4 {

	public static void main(String[] args) throws Exception {
		Reader r = Resources.getResourceAsReader("am/config/config.xml");
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		r.close();
		
		
		SqlSession ss = factory.openSession();
		
		List<EmpVO> e_list = ss.selectList("emp.all");
		
		System.out.printf("%6s | %-6s | %-11s | %-1s\r\n","Empno","Ename","Dname","City");
		System.out.println("============================================");
		
		for(int i=0; i< e_list.size(); i++) {
			EmpVO evo = e_list.get(i);
			
			DeptVO dvo = evo.getDvo();
			
			LocaVO lvo = dvo.getLvo();
			
			System.out.printf("%6s | %-6s | %-11s | %-1s\r\n",evo.getEmpno(),evo.getEname(),dvo.getDname(),lvo.getCity());			
		}
		System.out.println("============================================");
		
		
		

	}

}
