package pm.client;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pm.vo.DeptVO;
import pm.vo.EmpVO;
import pm.vo.LocaVO;

public class Main_Ex1 {

	public static void main(String[] args) throws Exception {
		
		Reader r = Resources.getResourceAsReader("pm/config/config.xml");
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		r.close();
		
		
		SqlSession ss = factory.openSession();
		
		List<DeptVO> d_list = ss.selectList("dept.all");
		
		for(int i=0; i<d_list.size();i++) {
			DeptVO dvo = d_list.get(i);
			
			LocaVO lvo = dvo.getLvo();
			
			List<EmpVO> e_list = dvo.getE_list();
			System.out.printf("부서코드: %-3s 부서명: %-11s 근무지: %-10s (%-2d명) \r\n",dvo.getDeptno(),dvo.getDname(),lvo.getCity(),e_list.size());
			System.out.println("------------------------------------------");
			// 구성원들 출력하는 반복문
			for(int j=0; j<e_list.size();j++) {
				EmpVO evo = e_list.get(j);
				System.out.printf("\t> 사번: %-6s | 이름: %-7s \r\n",evo.getEmpno(), evo.getEname());
			}// 구성원 반복문의 끝
			
			
			System.out.println();
			
			
		}
		
		
		
	}

}
