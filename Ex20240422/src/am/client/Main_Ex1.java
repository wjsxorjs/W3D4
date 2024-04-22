package am.client;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import am.vo.DataVO;

public class Main_Ex1 {

	public static void main(String[] args) throws Exception {
		// 1) 환경설정 파일과 연동되는 스트림 Reader 준비
		Reader r = Resources.getResourceAsReader("am/config/config.xml");
		
		// 2) Factory 생성
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		r.close();
		// ----- 여기까지가 한 번 밖에 사용하지 않는 명령어
		
		SqlSession ss = factory.openSession();
		
		List<DataVO> list = ss.selectList("emp_dept.all");
		
		System.out.println("[결과]");
		System.out.printf("%-10s | %-10s | %-10s | %-10s | %-10s | %-10s \r\n","Empno","Ename","Job","Sal","Deptno","Dname");
		System.out.printf("=============================================================================\r\n");
		for(int i=0; i<list.size(); i++) {
			// list로부터 vo를 하나 얻어낸다.
			DataVO vo = list.get(i);
			System.out.printf("%-10s | %-10s | %-10s | %-10s | %-10s | %-10s \r\n",vo.getEmpno(),vo.getEname(),vo.getJob(),vo.getSal(),vo.getDeptno(),vo.getDname());
		}
		System.out.printf("=============================================================================\r\n");
		
		if(ss != null) {
			ss.close();
		}

	}

}
