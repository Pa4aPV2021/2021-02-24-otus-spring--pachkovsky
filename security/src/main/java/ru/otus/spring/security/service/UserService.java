package ru.otus.spring.security.service;

import javax.annotation.PostConstruct;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.sun.istack.NotNull;

import ru.otus.spring.security.domain.User;
import ru.otus.spring.security.repository.UserRepository;
import ru.otus.spring.security.security.Role;
import ru.otus.spring.security.security.UserDetailsModel;

@Service
public class UserService implements UserDetailsService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostConstruct
	public void init() {
		if (!userRepository.findByName("user").isPresent()) {
			this.userRepository
					.save(User.builder()
							.name("user")
							.password(new BCryptPasswordEncoder().encode("password"))
							.role(Role.USER.name())
							.build());
		}
	}

	@Override
	public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("user " + username + " was not found"));
		return UserDetailsModel.builder()
				.username(user.getName())
				.password(user.getPassword())
				.authorities(ImmutableList.of(new SimpleGrantedAuthority(user.getRole())))
				.accountNonExpired(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.enabled(true)
				.build();
	}
	
}
