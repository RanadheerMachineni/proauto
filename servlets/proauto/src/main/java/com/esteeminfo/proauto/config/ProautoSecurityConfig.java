package com.esteeminfo.proauto.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class ProautoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService customUserDetailsService;
	
	final static Logger logger = Logger.getLogger(ProautoSecurityConfig.class);


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);

		/*auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery(
				"select user_id,password,true from employee where user_id=?")
			.authoritiesByUsernameQuery(
				"select user_id,role_id from employee_role where user_id=?");*/
		/*auth.inMemoryAuthentication().withUser("admin").password("admin").roles("admin").and()
		.withUser("dms").password("dms").roles("dms").and()
		.withUser("jobcard").password("jobcard").roles("jobcard").and()
		.withUser("costing").password("costing").roles("costing");*/

	}

	//.csrf() is optional, enabled by default, if using WebSecurityConfigurerAdapter constructor

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	    http.authorizeRequests().and().formLogin()
	    .loginProcessingUrl("/j_spring_security_check")
	    .loginPage("/login")
	    .defaultSuccessUrl("/dashboard").failureUrl("/login?error")
		.usernameParameter("userid").passwordParameter("password")
		.and()
		.logout().logoutSuccessUrl("/login?logout")
		.and()
		.csrf();
	    
	    
	    http.authorizeRequests().antMatchers("/dms").access("hasAnyRole('ROLE_admin','ROLE_dms')");
	    http.authorizeRequests().antMatchers("/jobcard").access("hasAnyRole('ROLE_admin','ROLE_jobcard')");
	    http.authorizeRequests().antMatchers("/costing").access("hasAnyRole('ROLE_admin','ROLE_costing')");

	    http.authorizeRequests().antMatchers("/creg").access("hasRole('ROLE_admin')");
	    http.authorizeRequests().antMatchers("/vreg").access("hasRole('ROLE_admin')");
	    http.authorizeRequests().antMatchers("/ereg").access("hasRole('ROLE_admin')");
		
	    http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/Access_Denied");

	}
		
	public void configure(WebSecurity web) throws Exception {
	   /* web
	        .ignoring()
	        .antMatchers("/css/**").and().ignoring()
	        .antMatchers("/images/**")
	        .and().ignoring().antMatchers("/login/**")
	    	;*/
	}
}