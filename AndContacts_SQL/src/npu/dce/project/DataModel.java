package npu.dce.project;

public class DataModel {

	private int theId;
	private String name, phone, email, postaladdr;
	
	public DataModel(int theId,String name, String phone, String email,String postaladdr) {
		super();
		this.theId = theId;
		this.name = name;
		//this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.postaladdr = postaladdr;
	}

	public void setTheId(int theId) {
		this.theId = theId;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPostaladdr(String postaladdr) {
		this.postaladdr = postaladdr;
	}

	public int getTheId() {
		return theId;
	}

	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getPostaladdr() {
		return postaladdr;
	}


}
