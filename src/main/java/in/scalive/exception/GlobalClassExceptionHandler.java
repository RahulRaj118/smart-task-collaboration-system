package in.scalive.exception;

import org.springframework.security.access.AccessDeniedException;

import io.jsonwebtoken.security.*;
import java.time.LocalTime;

import org.springframework.security.core.AuthenticationException;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalClassExceptionHandler {

	
	
	@ExceptionHandler(value=NoSuchFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchFound(NoSuchFieldException  exception){
		
		
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage(),LocalTime.now()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException exception){
		
		return new ResponseEntity<ErrorResponse> (new ErrorResponse(HttpStatus.FORBIDDEN.value(),exception.getMessage(),LocalTime.now()),HttpStatus.FORBIDDEN);
		
}
	
	@ExceptionHandler(value = AuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException exception){
		
		return new ResponseEntity<ErrorResponse> (new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),exception.getMessage(),LocalTime.now()),HttpStatus.UNAUTHORIZED );
	}
	@ExceptionHandler(value=SignatureException.class)
	public ResponseEntity<ErrorResponse> handleSignautre(SignatureException exception){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),LocalTime.now()),HttpStatus.BAD_GATEWAY)
;	}
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<ErrorResponse> handleAllException(Exception exception){
	
	return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage(),LocalTime.now()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
