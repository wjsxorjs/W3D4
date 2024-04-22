package test.client;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import test.vo.EmpVO;
import test.vo.MgrVO;

public class Main_Ex1 {

	public static void main(String[] args) throws Exception {
		Reader r = Resources.getResourceAsReader("test/config/config.xml");
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		r.close();
		
		SqlSession ss = factory.openSession();
		
		List<MgrVO> m_list = ss.selectList("mgr.all");
		
		
		for(int i=0; i<m_list.size();i++) {
			MgrVO mvo = m_list.get(i);
			System.out.printf("%-5s | %-10s | %-12s\r\n",mvo.getEmpno(),mvo.getEname(),mvo.getJob());
			List<EmpVO> e_list = mvo.getE_list();
			
				for(int j=0; j<e_list.size();j++) {
					EmpVO evo = e_list.get(j);
					System.out.printf("\t> %-5s | %-10s | %-12s\r\n",evo.getEmpno(),evo.getEname(),evo.getJob());
				}
			
			
		}

	}

}
