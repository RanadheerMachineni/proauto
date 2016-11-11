package com.esteeminfo.proauto.appconfig;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ProautoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	final static Logger logger = Logger.getLogger(ProautoSecurityConfig.class);


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery(
				"select user_name,password,true from users where user_name=?")
			.authoritiesByUsernameQuery(
				"select user_name,role from user_role where user_name=?");
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
	    
	    
	    http.authorizeRequests().antMatchers("/dms**").access("hasAnyRole('admin','dms')");
	    http.authorizeRequests().antMatchers("/jobcard**").access("hasAnyRole('admin','jobcard')");
	    http.authorizeRequests().antMatchers("/costing**").access("hasAnyRole('admin','costing')");

	    http.authorizeRequests().antMatchers("/creg**").access("hasRole('admin')");
	    http.authorizeRequests().antMatchers("/vreg**").access("hasRole('admin')");
	    http.authorizeRequests().antMatchers("/ereg**").access("hasRole('admin')");
		
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