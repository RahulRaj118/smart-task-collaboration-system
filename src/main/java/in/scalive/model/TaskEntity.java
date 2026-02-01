package in.scalive.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.scalive.model.enums.TaskPriority;
import in.scalive.model.enums.TaskStatusAdmin;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class TaskEntity {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;
	private String description;

	@Enumerated(EnumType.STRING)
	private TaskStatusAdmin status;
	@Enumerated(EnumType.STRING)
	private TaskPriority priority;

	private Integer createdByAdmin;

	private LocalDate dueDate;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "tasksId")
	@JsonIgnore
	private List<TaskAssignment> assignments;

	
	@PrePersist
	public void onCreation() {
		this.createdAt= LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void onUpdation() {
		this.updatedAt = LocalDateTime.now();
	}
	
	public TaskEntity() {

	}

	public TaskEntity(Integer id, String title, String description, TaskStatusAdmin status, TaskPriority priority,
			Integer createdByAdmin, LocalDate dueDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;

		this.createdByAdmin = createdByAdmin;
		this.dueDate = dueDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatusAdmin getStatus() {
		return status;
	}

	public void setStatus(TaskStatusAdmin status) {
		this.status = status;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public Integer getCreatedByAdmin() {
		return createdByAdmin;
	}

	public void setCreatedByAdmin(Integer createdByAdmin) {
		this.createdByAdmin = createdByAdmin;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<TaskAssignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<TaskAssignment> assignments) {
		this.assignments = assignments;
	}
	
	public void removeAssignment(TaskAssignment assignment) {
		assignments.remove(assignment);
	}
	
	public void addAssignment(TaskAssignment assignment) {
		assignments.add(assignment);
	}

	
	
	
}
