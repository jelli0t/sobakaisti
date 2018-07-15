/**
 * 
 */
package org.sobakaisti.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sobakaisti.util.CommitResult;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author jelles
 *
 */
@Component
public class AccountAuthenticationFailureHandler implements AuthenticationFailureHandler{
	private PrintWriter out;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		System.out.println("Login exception: "+exception.getClass());
		System.out.println("Login failure exception: "+exception.getMessage());
		
//		response.setContentType("text/plain");           
	        response.setHeader("Cache-Control", "no-cache");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		
//		final String data = exception.getMessage();
//		try{
//			out = response.getWriter();
//			out.print(data);
//			out.flush();
//		}catch(IOException ex){
//			System.out.println("Neuspesno upisivanje odgovora.");
//		}finally {
//			out.close();
//		}		
		
		String errorCode = null;
		if(exception instanceof AuthenticationCredentialsNotFoundException)
			errorCode = "login.exception.emptyCredential";
		else if(exception instanceof UsernameNotFoundException)
			errorCode = "login.exception.notFound";
		else if(exception instanceof BadCredentialsException)
			errorCode = "login.exception.badCredential";
		else
			errorCode = "login.exception.general";	
				
		response.sendRedirect("login?errorCode="+errorCode);
	}	
}
