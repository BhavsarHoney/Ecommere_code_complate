package com.einfo.Project.Ecommerce.config;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.einfo.Project.Ecommerce.Model.User;
import com.einfo.Project.Ecommerce.repo.UserRepo;
@Service
public class UserUserDetailsService  implements UserDetailsService {

	
	@Autowired
	UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userInfo = repo.findByEmail(username);
         return userInfo.map(UserUserDetails::new)
               .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
	}

	

	

}
