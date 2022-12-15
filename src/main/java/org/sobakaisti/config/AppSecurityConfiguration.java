package org.sobakaisti.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sobakaisti.security.Authority;
import org.sobakaisti.util.CommitResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages="org.sobakaisti.security")
public class AppSecurityConfiguration  extends WebSecurityConfigurerAdapter {

	private static final int BCRYPT_LOG_ITERATION = 12;
	
	private final AuthenticationSuccessHandler accountAuthenticationSuccessHandler;
	private final UserDetailsService dbUserDetailsService;

//	@Autowired
//	private AuthenticationProvider authenticationProvider;

	@Autowired
	public AppSecurityConfiguration(
			AuthenticationSuccessHandler accountAuthenticationSuccessHandler,
			UserDetailsService dbUserDetailsService
	) {
		this.accountAuthenticationSuccessHandler = accountAuthenticationSuccessHandler;
		this.dbUserDetailsService = dbUserDetailsService;
	}

	public AppSecurityConfiguration(boolean disableDefaults, AuthenticationSuccessHandler accountAuthenticationSuccessHandler, UserDetailsService dbUserDetailsService) {
		super(disableDefaults);
		this.accountAuthenticationSuccessHandler = accountAuthenticationSuccessHandler;
		this.dbUserDetailsService = dbUserDetailsService;
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
		
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/sbk-admin/**").hasAnyRole("ADMIN", "EDITOR", "WRITER")
		        .and().formLogin().loginPage("/login")
                .permitAll()
//                .loginProcessingUrl("/do.login")
                .usernameParameter("username")
                .passwordParameter("password")                
                .failureHandler(userAuthenticationFailureHandler())
                .defaultSuccessUrl("/sbk-admin")
//                .successHandler(accountAuthenticationSuccessHandler)
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.
			ignoring()
			.antMatchers("/resources/**");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCRYPT_LOG_ITERATION);
	}
	 
	 @Bean
	 public DaoAuthenticationProvider authProvider() {
		 DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
			daoProvider.setPasswordEncoder(passwordEncoder());
			daoProvider.setUserDetailsService(dbUserDetailsService);
//			ReflectionSaltSource saltHash = new ReflectionSaltSource();
//			saltHash.setUserPropertyToUse("username");
//			daoProvider.setSaltSource(saltHash);
	     return daoProvider;
	 }
	 
	 
	 @Bean
	 public AuthenticationFailureHandler userAuthenticationFailureHandler() {
		 AuthenticationFailureHandler failureHandler = new AuthenticationFailureHandler() {			
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				response.setHeader("Cache-Control", "no-cache");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				String errorCode = null;
				if(exception instanceof UsernameNotFoundException)
					errorCode = Authority.USER_NOT_FOUND_ERROR_CODE;
				else if(exception instanceof BadCredentialsException)
					errorCode = Authority.BAD_CREDENTIAL_ERROR_CODE;
				else
					errorCode = Authority.GENERAL_AUTH_ERROR_CODE;
				response.sendRedirect(request.getContextPath() + "/login?errorCode="+errorCode);
			}
		};
		return failureHandler;
	 }
	 	 
	 @Bean
	 public AccessDeniedHandler accessDeniedHandler() {
		 AccessDeniedHandler deniedHandler = new AccessDeniedHandler() {			
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				request.setAttribute("commitResult", new CommitResult(false, "Nemate odobren pristup ovom delu sajta."));
				request.getRequestDispatcher("/login").forward(request, response);			
			}
		};
		return deniedHandler;
	 }
}
