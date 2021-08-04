package ru.otus.spring.securityauth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sun.istack.NotNull;

import ru.otus.spring.securityauth.domain.Privilege;
import ru.otus.spring.securityauth.domain.Role;
import ru.otus.spring.securityauth.domain.User;
import ru.otus.spring.securityauth.repository.UserRepository;
import ru.otus.spring.securityauth.security.UserDetailsModel;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("user " + username + " was not found"));
		return UserDetailsModel.builder().username(user.getName()).password(user.getPassword())
				.authorities(getAuthorities(user.getRoles())).accountNonExpired(true).accountNonLocked(true)
				.credentialsNonExpired(true).enabled(true).build();
	}

	private List<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<? extends GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	private List<String> getPrivileges(List<Role> roles) {
		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			privileges.add(role.getName());
			collection.addAll(role.getPrivileges());
		}
		for (Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

}
