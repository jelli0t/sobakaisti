/**
 * 
 */
package org.sobakaisti.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author jelles
 * 
 */
@Component
public class AccountAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	private PrintWriter out;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("Success handler!");

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/plain");        
		response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
		final String data = "Success Login!";	// Uzmi poruku iz resource bundle
		try{
			out = response.getWriter();
			out.print(data);
			out.flush();			
		}catch(IOException ex){
			System.out.println("Neuspelo upisivanje odgovora.");
		}finally {
			out.close();
		}
		
	}

}
