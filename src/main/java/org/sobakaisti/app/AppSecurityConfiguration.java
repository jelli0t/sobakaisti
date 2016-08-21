package org.sobakaisti.app;

import org.sobakaisti.mvt.service.impl.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages="org.sobakaisti.mvt")
public class AppSecurityConfiguration  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AccountDetailsService accountDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.
//			inMemoryAuthentication()
//			.withUser("admin").password("admin").roles("ADMIN"); 
		auth.userDetailsService(accountDetailsService);
	}	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/movement").permitAll()
//			.anyRequest().authenticated()
//			.and()
//		.formLogin().loginPage("/login").permitAll()
//			.and()
//		.logout()
//			.permitAll();	
		
		http.authorizeRequests()
			.antMatchers("/movement").access("hasRole('ADMIN')")
		.and()
		    .formLogin().loginPage("/login").loginProcessingUrl("/login_process").failureUrl("/login?error")
		    .usernameParameter("username").passwordParameter("password")		
		.and()
		    .logout().logoutSuccessUrl("/login?logout")
		.and(); 	
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.
			ignoring()
			.antMatchers("/resources/**");
	}
}
