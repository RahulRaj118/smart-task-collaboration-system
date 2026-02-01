package in.scalive.service;

import java.security.SecureClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import in.scalive.dto.UpdateStatusUser;
import in.scalive.dto.UserAssignTask;
import in.scalive.exception.NoSuchFoundException;
import in.scalive.exception.NoTaskAssignedYetException;
import in.scalive.model.TaskAssignment;
import in.scalive.model.TaskEntity;
import in.scalive.model.UsersDet;
import in.scalive.model.enums.TaskStatusUser;
import in.scalive.repo.TaskAssignmentRepo;
import in.scalive.repo.TaskEntityRepo;
import in.scalive.repo.UsersDetailsRepository;
import jakarta.validation.ReportAsSingleViolation;

@Service
public class UserAssignTaskService {

	@Autowired
	private TaskAssignmentRepo assignmentRepo;

	@Autowired
	private UsersDetailsRepository userRepo;
	
	@Autowired 
	private TaskEntityRepo taskRepo;

	public UserAssignTask getAsssignTask() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Integer id = (Integer) authentication.getDetails();

		UsersDet usersDet = userRepo.findById(id).orElse(null);

		if (usersDet == null) {
			throw new NoSuchFoundException("sorry some error occur Please try again later ");
		}
		TaskAssignment assignment = assignmentRepo.findByUser(usersDet);
		if (assignment == null) {
			throw new NoTaskAssignedYetException("Task not assigned so enjoy");
		}
		UserAssignTask userAssignTask = new UserAssignTask();
userAssignTask.setUserId(id);
userAssignTask.setTaskTitle(assignment.getTasksId().getTitle());
userAssignTask.setDescription(assignment.getTasksId().getDescription());
userAssignTask.setStatus(assignment.getStatusUser() );
userAssignTask.setAssignedAt(assignment.getAssignedAt());
userAssignTask.setTaskId(assignment.getTasksId().getId());
return userAssignTask;
	}

	public String updateStatus(Integer userId,Integer taskId, UpdateStatusUser updateStatusUser) {
		
		UsersDet usersDet = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("Sorry Some Error occur in database please try again"));
		
		TaskEntity taskEntity = taskRepo.findById(taskId).orElseThrow(()-> new RuntimeException("Please check you task Id and then try again"));
		
	TaskAssignment assignment=	assignmentRepo.findByTasksIdAndUser(taskEntity, usersDet).orElseThrow(()-> new RuntimeException("Task not assigned to the user"));
	assignment.setStatusUser(updateStatusUser.getStatus());	
	assignmentRepo.save(assignment);
	return "Updated SuccessFully";
	
	}
	
}
