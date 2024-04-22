package ans.client;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ans.vo.EmpVO;

public class Main_Ex1 {

	public static void main(String[] args) throws Exception {
		Reader r = Resources.getResourceAsReader("ans/config/config.xml");
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		r.close();
		
		SqlSession ss = factory.openSession();
		
		List<EmpVO> m_list = ss.selectList("emp.mgr_list");
		
		for(int i=0; i< m_list.size(); i++) {
			// 부서장 객체
			EmpVO mvo = m_list.get(i);
			
			// 소속 사원 객체 리스트
			List<EmpVO> e_list = mvo.getE_list();
			
			System.out.printf("\r\n%s  |  %s  |  %s\r\n",mvo.getEmpno(),mvo.getEname(),mvo.getJob());
			
			for(int j=0;j<e_list.size();j++) {
				EmpVO evo = e_list.get(j);
				System.out.printf("\t%s | %s | %s\r\n",evo.getEmpno(),evo.getEname(),evo.getJob());
			}
		}

	}

}
