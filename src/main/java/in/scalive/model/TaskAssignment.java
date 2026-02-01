package in.scalive.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.GeneratorType;

import in.scalive.model.enums.TaskStatusUser;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class TaskAssignment {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="task_id",nullable = false)
	private TaskEntity tasksId;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private UsersDet user;
	
	@Enumerated(EnumType.STRING)
	private TaskStatusUser statusUser;
	private LocalDateTime assignedAt;
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void addTime() {
		assignedAt=LocalDateTime.now();
		updatedAt =LocalDateTime.now();
	}
	
	@PreUpdate
	public void updateTime() {
		updatedAt =LocalDateTime.now();
	}
	
	
	public TaskEntity getTasksId() {
		return tasksId;
	}
	public void setTasksId(TaskEntity tasksId) {
		this.tasksId = tasksId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public UsersDet getUser() {
		return user;
	}
	public void setUser(UsersDet user) {
		this.user = user;
	}
	public TaskStatusUser getStatusUser() {
		return statusUser;
	}
	public void setStatusUser(TaskStatusUser statusUser) {
		this.statusUser = statusUser;
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
