package injung.model;

import java.io.Serializable;

public class EmployeeDto implements Serializable {
	private static final long serialVersionUID = -7362786764406975154L;
	
	private int employeeId;
	private String name;
	private String birth;
	private String team;
	private String level;
	private String role;
	private String mobile;
	private String workPhone;
	private String eMail;
	private String location;
	private String password;
	private String photo;
	private String lostIdQuestion;
	private String lostIdAnswer;

	public EmployeeDto() {
		setEmployeeId(0);
		setName("-");
		setBirth("-");
		setTeam("-");
		setLevel("-");
		setRole("-");
		setMobile("-");
		setWorkPhone("-");
		seteMail("-");
		setLocation("-");
		setPhoto("no_avatar.jpg");
	}

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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
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

	@Override
	public String toString() {
		return "Employee (ID:" + employeeId + ", name:  " + name + ")";
	}

}
