package in.scalive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import in.scalive.model.UsersDet;
import in.scalive.repo.UsersDetailsRepository;

@Component
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersDetailsRepository uRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UsersDet usersDet = uRepo.findByEmail(email).orElse(null);
		if(usersDet==null) {
			throw new RuntimeException("Email or password invalid");
		}
		
		return User.withUsername(email)
				.password(usersDet.getPassword())
				.roles(usersDet.getRole())
				.build();
		
		
		
	}
	

}
