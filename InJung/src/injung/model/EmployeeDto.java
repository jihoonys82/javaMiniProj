package injung.model;

import java.sql.Date;

public class EmployeeDto {

	private int employeeId;
	private String name;
	private Date birth;
	private int team;
	private String level;
	private String role;
	private int mobile;
	private int workPhone;
	private String eMail;
	private String location;
	private String password;
	private String photo;
	private String lostIdQuestion;
	private String lostIdAnswer;
	
	
	
	
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public int getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(int workPhone) {
		this.workPhone = workPhone;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getLostIdQuestion() {
		return lostIdQuestion;
	}
	public void setLostIdQuestion(String lostIdQuestion) {
		this.lostIdQuestion = lostIdQuestion;
	}
	public String getLostIdAnswer() {
		return lostIdAnswer;
	}
	public void setLostIdAnswer(String lostIdAnswer) {
		this.lostIdAnswer = lostIdAnswer;
	}
	
	
}
