package homework;

public class LocaVO {
	private String loc_code, city;

	public LocaVO() {}
	
	public LocaVO(String loc_code, String city) {
		super();
		this.loc_code = loc_code;
		this.city = city;
	}


	public void setLoc_code(String loc_code) {
		this.loc_code = loc_code;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	
	
	public String getLoc_code() {
		return loc_code;
	}

	public String getCity() {
		return city;
	}

}
