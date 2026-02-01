package in.scalive.dto;

import java.time.LocalDateTime;

import in.scalive.model.enums.TaskStatusUser;

public class UserAssignTask {

	private Integer userId;
	
	private String taskTitle;
	private String description;
	private TaskStatusUser status;
	private LocalDateTime assignedAt;
	
	private Integer taskId;
	
	
	
	
	
	
	
	
	
	public UserAssignTask() {
		
	}
	public UserAssignTask(Integer userId, String taskTitle, String description, TaskStatusUser status,
			LocalDateTime assignedAt) {
		
		this.userId = userId;
		this.taskTitle = taskTitle;
		this.description = description;
		this.status = status;
		this.assignedAt = assignedAt;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TaskStatusUser getStatus() {
		return status;
	}
	public void setStatus(TaskStatusUser status) {
		this.status = status;
	}
	public LocalDateTime getAssignedAt() {
		return assignedAt;
	}
	public void setAssignedAt(LocalDateTime assignedAt) {
		this.assignedAt = assignedAt;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	
	
	
}
