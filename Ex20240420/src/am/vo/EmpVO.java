package am.vo;

import java.util.List;

public class EmpVO {
	
	private String empno, ename, job, sal;
	
	// 부서정보를 의미하는 객
	private DeptVO dvo;
	

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}
	
	public void setDvo(DeptVO dvo) {
		this.dvo = dvo;
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

	public String getSal() {
		return sal;
	}

	public DeptVO getDvo() {
		return dvo;
	}


	

}
