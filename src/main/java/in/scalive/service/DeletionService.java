package in.scalive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.scalive.model.TaskEntity;
import in.scalive.model.UsersDet;
import in.scalive.repo.TaskAssignmentRepo;
import in.scalive.repo.TaskEntityRepo;
import in.scalive.repo.UsersDetailsRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeletionService {

	@Autowired
	private TaskAssignmentRepo assignmentRepo;
	
	@Autowired
	private UsersDetailsRepository repo;
	@Autowired
	private TaskEntityRepo taskEntityRepo;

	
	public String deleteTask(Integer taksId) {
		
		TaskEntity taskEntity = taskEntityRepo.findById(taksId).orElseThrow(()->new RuntimeException("Check your taskID"));
		
		if(assignmentRepo.existsByTasksId(taskEntity)) {
			assignmentRepo.deleteByTasksId(taskEntity);
		}
	taskEntityRepo.deleteById(taksId);
		
		return "Task deleted SuccessFully";
	}
	public String deleteTaskFromUser(Integer taskId,Integer userId) {
		
		TaskEntity entity = taskEntityRepo.findById(taskId).orElse(null);
		if(entity==null) throw new RuntimeException("Check your task id ");
		UsersDet usersDet = repo.findById(userId).orElseThrow(()->new RuntimeException("Please check your userid "));
		
	if(	assignmentRepo.existsByTasksIdAndUser(entity, usersDet)) {
	assignmentRepo.deleteByTasksIdAndUser(entity, usersDet);
	}
	
	return "Deleted SuccessFully";
	
	}
}
