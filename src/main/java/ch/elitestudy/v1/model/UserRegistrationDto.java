package ch.elitestudy.v1.model;

import java.sql.Timestamp;

public class UserRegistrationDto {
	private String userName;
	private String email;
	private String password;
	private Timestamp timestamp;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		this.timestamp = ts;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



	public Timestamp getTimestamp() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		return ts;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
