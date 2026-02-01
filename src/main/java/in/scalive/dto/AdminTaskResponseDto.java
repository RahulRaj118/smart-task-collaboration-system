package in.scalive.dto;

import java.time.LocalDateTime;

import in.scalive.model.enums.TaskStatusUser;

public class AdminTaskResponseDto {

	private Integer userId;
	
	private String userName;
	
	private TaskStatusUser status;
	private LocalDateTime assignedAt;
	private LocalDateTime updatedAt;
	
	
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	
	
}
