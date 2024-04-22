package test.vo;

import java.util.List;

public class MgrVO {
	private String empno, ename, job;
	
	private List<EmpVO> e_list;

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setE_list(List<EmpVO> e_list) {
		this.e_list = e_list;
	}

	
	
	public String getEmpno() {
		return empno;
	}

	public String getEname() {
		return ename;
	}

	public String getJob() {
		return job;
	}

	public List<EmpVO> getE_list() {
		return e_list;
	}
	
	
	

}
