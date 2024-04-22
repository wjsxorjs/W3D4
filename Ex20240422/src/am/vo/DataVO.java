package am.vo;

public class DataVO {
	// emp테이블과 dept 테이블에서 얻어지는 결과 중
	// 필요한 칼럼들을 멤버변수로 선언
	
	private String empno, ename, job, sal, deptno, dname;

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

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	public void setDname(String dname) {
		this.dname = dname;
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

	public String getDeptno() {
		return deptno;
	}

	public String getDname() {
		return dname;
	} 
	
	
	
	
	
}
