package sec01.ex01;

import java.util.Date;

public class MemberVO {

	
	private String name, id, pwd, email;
	private Date joinDate;
	
	public MemberVO(String id, String pwd, String name, String email, Date joinDate) {
		this.name=name;
		this.id= id;
		this.pwd= pwd;
		this.email= email;
		this.joinDate= joinDate;
		
	}
	
	public MemberVO(String id, String pwd, String name, String email) {
		this.name=name;
		this.id= id;
		this.pwd= pwd;
		this.email= email;
		
	}

	public MemberVO(String id) {
		this.id= id;
	}

	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	
	
}
