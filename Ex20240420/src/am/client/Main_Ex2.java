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

public class Main_Ex2 {

	public static void main(String[] args) throws Exception {
		Reader r = Resources.getResourceAsReader("am/config/config.xml");
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		r.close();
		// 여기까지 한번만 수행한다.
		
		SqlSession ss = factory.openSession();
		
		List<EmpVO> e_list = ss.selectList("emp.all");
		System.out.printf("%6s | %7s | %10s | %8s | %7s | %12s | %9s | %11s \r\n", "Empno","Ename","Job","Sal",
				"Deptno","Dname",
				"Loc_code","City");
		System.out.println("---------------------------------------------------------------------------------------------");
		
		for(int i=0; i<e_list.size();i++) {
			// 사원정보 객체 얻기
			EmpVO evo = e_list.get(i);
			// 해당 사원의 부서정보 얻기
			DeptVO dvo = evo.getDvo();
			// 해당 부서의 도시정보 얻기
			LocaVO lvo = dvo.getLvo();
			
			System.out.printf("%6s | %7s | %10s | %8s | %7s | %12s | %9s | %11s \r\n", evo.getEmpno(),evo.getEname(),evo.getJob(),evo.getSal(),
																			dvo.getDeptno(),dvo.getDname(),
																			lvo.getLoc_code(),lvo.getCity());
		}
		System.out.println("---------------------------------------------------------------------------------------------");
		
		if(ss != null) {
			ss.close();
		}

	}

}
