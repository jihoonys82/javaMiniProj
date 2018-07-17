package injung.model;

import java.io.Serializable;

public class CalendarDto implements Serializable {
	private static final long serialVersionUID = -4288325269901864750L;

	private int calendarId;
	private String taskName;
	private String startDate;
	private String expectEndDate;
	private String actualEndDate;
	private String status;
	private String note;
	private int ownerId;
	
	public CalendarDto() {
		
	}
	
	public int getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(int calendarId) {
		this.calendarId = calendarId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getExpectEndDate() {
		return expectEndDate;
	}
	public void setExpectEndDate(String expectEndDate) {
		this.expectEndDate = expectEndDate;
	}
	public String getActualEndDate() {
		return actualEndDate;
	}
	public void setActualEndDate(String actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		return "Calendar (id:" + calendarId + ", taskName:" + taskName +")";
	}
}
