package in.scalive.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.scalive.dto.AdminTaskResponseDto;
import in.scalive.dto.AssignTaskDto;
import in.scalive.model.TaskEntity;
import in.scalive.service.DeletionService;
import in.scalive.service.TaskAssignmentService;
import in.scalive.service.TaskEntityService;
import jakarta.persistence.Entity;

import java.util.List;

import org.hibernate.grammars.hql.HqlParser.SecondContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private TaskEntityService service;
	
	@Autowired 
	private TaskAssignmentService taskAssignmentService;
	
	@Autowired
	private DeletionService deletionService;
	
	
	
	@PostMapping("/createProject")
	public ResponseEntity<TaskEntity> saveDetails(@RequestBody TaskEntity taskEntity){
		
		return new ResponseEntity<TaskEntity>(service.saveDetails(taskEntity),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<TaskEntity>> getAll(){
		return new ResponseEntity<List<TaskEntity>>(service.getAllTask(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskEntity> getById(@PathVariable Integer id){
		return new ResponseEntity<TaskEntity>(service.getById(id),HttpStatus.OK);
	}
	
	
	@PostMapping("/assign")
	public ResponseEntity<String> assignTaskToUser(@RequestBody AssignTaskDto taskDto){
	return new ResponseEntity<String>(taskAssignmentService.addAssignment(taskDto),HttpStatus.CREATED);
	
	}
	
	
	@GetMapping("/tasks/{taskId}/progress")
	public List<AdminTaskResponseDto> getAll(@PathVariable Integer taskId){
		return taskAssignmentService.getAllUser(taskId);
	}
	
	
	@DeleteMapping("/task/delete/{id}")
	public ResponseEntity<String> deletetask(@PathVariable Integer id){
		return new ResponseEntity<String>(deletionService.deleteTask(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/taskUser/{taskId}/delete/{userId}")
	public ResponseEntity<String> deleteUserAndTask(@PathVariable Integer taskId,@PathVariable Integer userId){
		return new ResponseEntity<String>(deletionService.deleteTaskFromUser(taskId, userId),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public Integer getDashboard() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Integer userId=(Integer) authentication.getDetails();
		return userId ;
		
	}
	
}
