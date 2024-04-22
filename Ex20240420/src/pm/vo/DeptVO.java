package pm.vo;

import java.util.List;

public class DeptVO {
	
	private String deptno, dname;
	
	// 해당 부서에 속한 사원들
	private List<EmpVO> e_list;

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public void setE_list(List<EmpVO> e_list) {
		this.e_list = e_list;
	}

	
	
	
	public String getDeptno() {
		return deptno;
	}

	public String getDname() {
		return dname;
	}

	public List<EmpVO> getE_list() {
		return e_list;
	} 
	
	
}
