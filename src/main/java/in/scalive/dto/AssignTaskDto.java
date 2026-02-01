package in.scalive.dto;

import java.util.List;

public class AssignTaskDto {

	private Integer taskId;
	private List<Integer> userId;
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public List<Integer> getUserId() {
		return userId;
	}
	public void setUserId(List<Integer> userId) {
		this.userId = userId;
	}
	
}
