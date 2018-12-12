package Bean;

import java.sql.Date;
import java.sql.Time;

public class TaskBean {
private int taskId;

private int userId;
private String taskName;
private String taskState;
public TaskBean(int taskId, int userId, String taskName, String taskState, String year, String month, String day,
		int taskScore) {
	super();
	this.taskId = taskId;
	this.userId = userId;
	this.taskName = taskName;
	this.taskState = taskState;
	Year = year;
	Month = month;
	Day = day;
	this.taskScore = taskScore;
}
private int dateTime;
private String Year;
private String Month;
private String Day;
private int taskScore;
private String taskTag;
public String getTaskTag() {
	return taskTag;
}
public void setTaskTag(String taskTag) {
	this.taskTag = taskTag;
}
public TaskBean() {
	super();
	// TODO Auto-generated constructor stub
}
public int getTaskId() {
	return taskId;
}
public void setTaskId(int taskId) {
	this.taskId = taskId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getTaskName() {
	return taskName;
}
public void setTaskName(String taskName) {
	this.taskName = taskName;
}
public String getTaskState() {
	return taskState;
}
public void setTaskState(String taskState) {
	this.taskState = taskState;
}
public int getDateTime() {
	return dateTime;
}
public void setDateTime(int dateTime) {
	this.dateTime = dateTime;
}
public String getYear() {
	return Year;
}
public void setYear(String year) {
	Year = year;
}
public String getMonth() {
	return Month;
}
public void setMonth(String month) {
	Month = month;
}
public String getDay() {
	return Day;
}
public void setDay(String day) {
	Day = day;
}
public int getTaskScore() {
	return taskScore;
}
public void setTaskScore(int taskScore) {
	this.taskScore = taskScore;
}

}