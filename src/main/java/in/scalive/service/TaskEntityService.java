package in.scalive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.scalive.exception.NoSuchFoundException;
import in.scalive.model.TaskEntity;
import in.scalive.repo.TaskAssignmentRepo;
import in.scalive.repo.TaskEntityRepo;

@Service
public class TaskEntityService {
	
	@Autowired
	private TaskEntityRepo repo;
	
	public TaskEntity saveDetails(TaskEntity taskEntity) {
		
		return repo.save(taskEntity);
	}
	
	public List<TaskEntity> getAllTask(){
		
		return repo.findAll();
	}
	
	public TaskEntity getById(Integer id) {
	TaskEntity taskEntity = repo.findById(id).orElse(null);
	if(taskEntity==null) {
		throw new NoSuchFoundException("Check your id "+id);
	}
	return taskEntity;
	}

}
