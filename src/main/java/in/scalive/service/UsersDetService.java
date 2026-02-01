package in.scalive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.scalive.dto.LoginCredentials;
import in.scalive.model.UsersDet;
import in.scalive.repo.UsersDetailsRepository;

@Service
public class UsersDetService {

	@Autowired
	private UsersDetailsRepository repo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTService service;
	
	BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);
	public UsersDet savePersonDetail(UsersDet userDet) {
		userDet.setPassword(encoder.encode(userDet.getPassword()));
	return repo.save(userDet);
		

	}
	public String loginDetails(LoginCredentials loginCredentials) {
	
	Authentication authentication = 	authManager.authenticate(new UsernamePasswordAuthenticationToken(loginCredentials.getEmail(),loginCredentials.getPassword()));
		
		if(authentication.isAuthenticated()) {
			String email = authentication.getName();
			UsersDet usersDet = repo.findByEmail(email).orElse(null);
			String token=service.generateToken(email, usersDet);
			
			return token;
		}
		return "fail";
	}
	
}
