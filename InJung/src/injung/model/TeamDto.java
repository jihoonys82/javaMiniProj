package injung.model;

import java.io.Serializable;

public class TeamDto implements Serializable {
	private static final long serialVersionUID = -383049198919544480L;
	
	private String teamName;
	private String teamRole;
	private String teamLeaderId;
	private String teamLeaderName; // it is not in Team table at Database

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamRole() {
		return teamRole;
	}

	public void setTeamRole(String teamRole) {
		this.teamRole = teamRole;
	}

	public String getTeamLeaderId() {
		return teamLeaderId;
	}

	public void setTeamLeaderId(String teamLeaderId) {
		this.teamLeaderId = teamLeaderId;
	}

	public String getTeamLeaderName() {
		return teamLeaderName;
	}

	public void setTeamLeaderName(String teamLeaderName) {
		this.teamLeaderName = teamLeaderName;
	}

	public TeamDto() { // teamDTO »ý¼ºÀÚ

	}
}
