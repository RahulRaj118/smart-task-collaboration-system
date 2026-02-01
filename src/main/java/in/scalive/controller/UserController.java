package in.scalive.controller;

import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.scalive.dto.UpdateStatusUser;
import in.scalive.dto.UserAssignTask;
import in.scalive.service.UserAssignTaskService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserAssignTaskService service;
	
	@GetMapping("/task")
public ResponseEntity<UserAssignTask> getAssignTask(){
		
	return new ResponseEntity<UserAssignTask>(service.getAsssignTask(),HttpStatus.OK);
	}
	
	@PostMapping("/update/task/{taskId}/status")
	public ResponseEntity<String> updateStatus(@PathVariable Integer taskId,@RequestBody UpdateStatusUser updateStatusUser){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Integer userId= (Integer)authentication.getDetails();
		return new ResponseEntity<String>(service.updateStatus(userId, taskId, updateStatusUser),HttpStatus.ACCEPTED);
	}
}
