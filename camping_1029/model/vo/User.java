package miniproject.camping.user.model.vo;

import java.sql.Date;
import java.sql.SQLException;

public class User {
	private int user_No;
	private String user_ID;
	private String user_pw;
	private String user_Name;
	private String user_Email;
	private String user_Phone;
	private Date user_Birthd;

	public User(int user_No, String user_ID, String user_pw, String user_Name, String user_Email, String user_Phone,
			java.sql.Date up_birthd) {
		super();
		this.user_No = user_No;
		this.user_ID = user_ID;
		this.user_pw = user_pw;
		this.user_Name = user_Name;
		this.user_Email = user_Email;
		this.user_Phone = user_Phone;
		this.user_Birthd = up_birthd;
	}
	
	public User(String user_ID, String user_pw, String user_Name, String user_Email, String user_Phone,
			java.util.Date up_birthd) {
		this.user_ID = user_ID;
		this.user_pw = user_pw;
		this.user_Name = user_Name;
		this.user_Email = user_Email;
		this.user_Phone = user_Phone;
		this.user_Birthd = (Date) up_birthd;
	}
	
	public int getUser_No() {
		return user_No;
	}
	public void setUser_No(int user_No) {
		this.user_No = user_No;
	}
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getUser_Email() {
		return user_Email;
	}
	public void setUser_Email(String user_Email) {
		this.user_Email = user_Email;
	}
	public String getUser_Phone() {
		return user_Phone;
	}
	public void setUser_Phone(String user_Phone) {
		this.user_Phone = user_Phone;
	}

	public Date getUser_Birthd() {
		return user_Birthd;
	}
	public void setUser_Birthd(Date user_Birthd) {
		this.user_Birthd = user_Birthd;
}
}