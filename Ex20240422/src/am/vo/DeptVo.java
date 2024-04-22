package am.vo;

public class DeptVO {
	private String deptno, dname, loc_code;
	private LocaVO lvo; 

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public void setLoc_code(String loc_code) {
		this.loc_code = loc_code;
	}

	public void setLvo(LocaVO lvo) {
		this.lvo = lvo;
	}

	
	
	public String getDeptno() {
		return deptno;
	}

	public String getDname() {
		return dname;
	}

	public String getLoc_code() {
		return loc_code;
	}

	public LocaVO getLvo() {
		return lvo;
	}
	
	

}
