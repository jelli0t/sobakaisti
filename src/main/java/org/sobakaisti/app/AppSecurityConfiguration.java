package org.sobakaisti.app;

import org.sobakaisti.mvt.security.AccountAuthenticationProvider;
import org.sobakaisti.mvt.service.impl.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages="org.sobakaisti.mvt")
public class AppSecurityConfiguration  extends WebSecurityConfigurerAdapter{
	
//	@Autowired
//	private UserDetailsService accountDetailsService;
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Override
		protected AuthenticationManager authenticationManager() throws Exception {
			// TODO Auto-generated method stub
			return super.authenticationManager();
		}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.
////			inMemoryAuthentication()
////			.withUser("admin").password("admin").roles("ADMIN"); 
////		auth.userDetailsService(accountDetailsService);
//		auth.authenticationProvider(authenticationProvider);
//	}	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			 .anyRequest().authenticated()
		        .and()
		        .formLogin()
		        .loginPage("/login")
		        .permitAll();	
		
//		http.authorizeRequests()
//			.antMatchers("/movement").access("hasRole('ADMIN')")
//		.and()
//		    .formLogin().loginPage("/login") //.loginProcessingUrl("/do.login").failureUrl("/login?error")
//		    .usernameParameter("principal").passwordParameter("credential")
//		.and()
//		    .logout().logoutSuccessUrl("/home")
//		.and(); 
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.
			ignoring()
			.antMatchers("/resources/**");
	}
	
//	@Bean
//	UsernamePasswordAuthenticationFilter  usernamePasswordAuthenticationFilter (){
//		UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
//		filter.setUsernameParameter("principal");
//		filter.setPasswordParameter("credential");
//	
//		return filter;
//	}
}
