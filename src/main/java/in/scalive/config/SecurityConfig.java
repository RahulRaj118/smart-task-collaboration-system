package in.scalive.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;

import in.scalive.service.JWTService;
import in.scalive.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
	http
	.csrf(csrf->csrf.disable())
	.authenticationProvider(authenticationProvider(myUserDetailsService))
	.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	
	.authorizeHttpRequests(request->request.requestMatchers(HttpMethod.POST,"/information/register","/information/login").permitAll()
			
			.requestMatchers("/admin/**").hasRole("ADMIN")
		    .requestMatchers("/user/**").hasRole("USER")
			.anyRequest().authenticated())
	.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	;
	
	return http.build();
	
	}
	
	
	@Bean
	AuthenticationProvider authenticationProvider(MyUserDetailsService service) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(service);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		
		return provider;
	}
	
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		
		return configuration.getAuthenticationManager();
	}
}
