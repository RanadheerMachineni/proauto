package com.esteeminfo.proauto.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esteeminfo.proauto.dao.EmployeeDao;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.Role;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	
	@Autowired(required=true)
	private EmployeeDao employeeDao ;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String ssoId)
			throws UsernameNotFoundException {
		Employee employee = employeeDao.findByUser(ssoId);
		List<Role> listOfRoles = null;
		if(employee==null){
			System.out.println("Employee not found");
			throw new UsernameNotFoundException("Employee not found");
		}else{
			System.out.println("Employee found, "+employee.getFirstName());
			listOfRoles = employee.getRoles();
		}
		for(Role role : listOfRoles){
			System.out.println("Employee role = "+role.getName()+", "+role.getRoleId());
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Role role : employee.getRoles()){
			//authorities.add(new SimpleGrantedAuthority(role.getRoleId()));
		}
		authorities.add(new SimpleGrantedAuthority("ROLE_admin"));

			return new org.springframework.security.core.userdetails.User(employee.getUserId(), employee.getPassword(), 
				 true, true, true, true, authorities);
	}

	
	private List<GrantedAuthority> getGrantedAuthorities(Employee employee){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Role role : employee.getRoles()){
			authorities.add(new SimpleGrantedAuthority(role.getRoleId()));
		}
		System.out.print("authorities :"+authorities);
		return authorities;
	}
	
}
