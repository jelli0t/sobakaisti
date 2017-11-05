package org.sobakaisti.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages="org.sobakaisti.mvt")
public class AppSecurityConfiguration  extends WebSecurityConfigurerAdapter{
	@Autowired
	private AuthenticationFailureHandler accountAuthenticationFailureHandler;
	@Autowired
	private AuthenticationSuccessHandler accountAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;	
	
	@Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .authenticationProvider(this.authenticationProvider);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/mvt").authenticated()
		        .and()
		        .formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/do.login")
                .usernameParameter("principal")
                .passwordParameter("credential")                
                .failureHandler(accountAuthenticationFailureHandler)
                .successHandler(accountAuthenticationSuccessHandler);

	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.
			ignoring()
			.antMatchers("/resources/**");
	}
}