package in.scalive.utils;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	private static final String SECRET_KEY =
	        "mysecretkeymysecretkeymysecretkey123"; // >= 32 chars

	private static final long EXPIRATION_TIME =
	        1000 * 60 * 60; // 1 hour

	
}
