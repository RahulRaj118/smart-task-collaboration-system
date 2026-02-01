package in.scalive.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.scalive.service.JWTService;
import in.scalive.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private JWTService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	String authHeader=	request.getHeader("Authorization");
	String token= null;
	String userEmail= null;
	if(authHeader!=null && authHeader.startsWith("Bearer")) {
		token = authHeader.substring("Bearer ".length()).trim();
		userEmail = service.extractUserName(token);
		
	}
		
	if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(userEmail);
		if(service.validateToken(token, userDetails)) {
			Integer userId= service.extractUserID(token);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
		authToken.setDetails(userId);
		SecurityContextHolder.getContext().setAuthentication(authToken);
		
		}
	}
	filterChain.doFilter(request, response);
	}

}
