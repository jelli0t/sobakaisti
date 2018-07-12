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
		
		CommitResult result = null;
		if(exception instanceof UsernameNotFoundException)
			result = new CommitResult(false, "cannot find a user");
		else if(exception instanceof BadCredentialsException)
			result = new CommitResult(false, "check your password");
		else
			result = new CommitResult(false, "dogodila se greska prilikom logovanja!");	
				
		request.getSession().setAttribute("commitResult", result);
		request.getSession().setAttribute("poruka", "Grska pri logovanju!");
		response.sendRedirect("login?error=true?message=greska");
	}	
}
