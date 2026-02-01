package in.scalive.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.stereotype.Service;

import in.scalive.dto.AdminTaskResponseDto;
import in.scalive.dto.AssignTaskDto;
import in.scalive.exception.NoSuchFoundException;
import in.scalive.model.TaskAssignment;
import in.scalive.model.TaskEntity;
import in.scalive.model.UsersDet;
import in.scalive.model.enums.TaskStatusUser;
import in.scalive.repo.TaskAssignmentRepo;
import in.scalive.repo.TaskEntityRepo;
import in.scalive.repo.UsersDetailsRepository;

@Service
public class TaskAssignmentService {

	
	@Autowired
	private TaskAssignmentRepo assignmentRepo;
	@Autowired
	private TaskEntityRepo entityRepo;
	
	@Autowired
	private UsersDetailsRepository userRepo;
	
	
	
	public String addAssignment(AssignTaskDto assignTaskDto) {
		TaskEntity taskEntity = entityRepo.findById(assignTaskDto.getTaskId()).orElse(null);
	if(taskEntity ==null) {
		throw new NoSuchFoundException("Check your details");
		
	}
	List<Integer> list = assignTaskDto.getUserId();
	for(Integer userId:list) {
		UsersDet user = userRepo.findById(userId).orElseThrow(()-> new NoSuchFoundException("Check your user id "));
	if(assignmentRepo.existsByTasksIdAndUser(taskEntity, user)) {
		continue;
	}
	TaskAssignment assignment = new TaskAssignment();
	assignment.setTasksId(taskEntity);
	assignment.setUser(user);
	assignment.setStatusUser(TaskStatusUser.PENDING);
	assignmentRepo.save(assignment);
	
	}
	return "addedd scuccessfully";
	}

	
	//getAll
	
	public List<AdminTaskResponseDto> getAllUser(Integer taskId){
		TaskEntity taskEntity = entityRepo.findById(taskId).orElseThrow(()->new RuntimeException("Check your task id"));
	List<TaskAssignment>list = 	assignmentRepo.findByTasksId(taskEntity);
	List<AdminTaskResponseDto> adminTaskList= new ArrayList<>();
	
	for(TaskAssignment assignment:list) {
		AdminTaskResponseDto adminTaskResponseDto = new AdminTaskResponseDto();
		adminTaskResponseDto.setUserId(assignment.getUser().getId());
		 adminTaskResponseDto.setStatus(assignment.getStatusUser());
		 adminTaskResponseDto.setAssignedAt(assignment.getAssignedAt());
		 adminTaskResponseDto.setUpdatedAt(assignment.getUpdatedAt());
		 adminTaskResponseDto.setUserName(assignment.getUser().getName());
		 adminTaskList.add(adminTaskResponseDto);
	}
	return adminTaskList;
	
	}
}
