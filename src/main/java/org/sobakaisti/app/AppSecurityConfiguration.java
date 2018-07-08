package org.sobakaisti.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages="org.sobakaisti.security")
public class AppSecurityConfiguration  extends WebSecurityConfigurerAdapter{
	@Autowired
	private AuthenticationFailureHandler accountAuthenticationFailureHandler;
	@Autowired
	private AuthenticationSuccessHandler accountAuthenticationSuccessHandler;
//	
//	@Autowired
//	private AuthenticationProvider authenticationProvider;	
	
	@Autowired
	private UserDetailsService dbUserDetailsService;
	
	
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
			.antMatchers("/sbk-admin").authenticated()
		        .and()
		        .formLogin()
                .loginPage("/login")
                .permitAll()
//                .loginProcessingUrl("/do.login")
                .usernameParameter("username")
                .passwordParameter("password")                
                .failureHandler(accountAuthenticationFailureHandler)
//                .successHandler(accountAuthenticationSuccessHandler)
                ;

	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.
			ignoring()
			.antMatchers("/resources/**");
	}
	
	
	
	 @Bean
	 public Md5PasswordEncoder md5PasswordEncoder(){
		 Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		 md5.setEncodeHashAsBase64(true);
		 md5.setIterations(32);
		 return md5;
	 }
	 
	 @Bean
	 public DaoAuthenticationProvider authProvider() {
		 DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
//			daoProvider.setPasswordEncoder(md5PasswordEncoder());
			daoProvider.setUserDetailsService(dbUserDetailsService);
//			ReflectionSaltSource saltHash = new ReflectionSaltSource();
//			saltHash.setUserPropertyToUse("username");
//			daoProvider.setSaltSource(saltHash);
	     return daoProvider;
	 }
}
