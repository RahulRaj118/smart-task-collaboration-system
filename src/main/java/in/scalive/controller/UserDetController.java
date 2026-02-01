package in.scalive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.scalive.dto.LoginCredentials;
import in.scalive.model.UsersDet;
import in.scalive.service.UsersDetService;

@RestController
@RequestMapping("/information")
public class UserDetController {
	
	@Autowired
	private UsersDetService service;

	@GetMapping("/hello")
	public String hello() {
		return "Welcome in Alien Earth";
	}
	
	@PostMapping("/register")
	public ResponseEntity<UsersDet> register(@RequestBody UsersDet usersDet){
		
		return new ResponseEntity<UsersDet>(service.savePersonDetail(usersDet),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginCredentials(@RequestBody LoginCredentials loginCredentials){
		return new ResponseEntity<String>(service.loginDetails(loginCredentials),HttpStatus.OK);
	}
}
