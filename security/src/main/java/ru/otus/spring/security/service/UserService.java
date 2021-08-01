package ru.otus.spring.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sun.istack.NotNull;

import ru.otus.spring.security.domain.User;
import ru.otus.spring.security.repository.UserRepository;
import ru.otus.spring.security.security.UserDetailsModel;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("user " + username + " was not found"));
		return UserDetailsModel.builder().username(user.getName()).password(user.getPassword()).authorities(null)
				.accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).enabled(true).build();
	}

}
