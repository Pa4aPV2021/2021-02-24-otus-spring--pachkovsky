package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import ru.otus.spring.domain.User;

@Service
public class AuthorizationServiceImpl implements AuthorizationService  {

	private User authorizedUser;
	private final OutputInputService outputInputService;

	public AuthorizationServiceImpl(OutputInputService outputInputService) {
		this.outputInputService = outputInputService;
	}

	public User getAuthorizedUser() {
		return authorizedUser;
	}

	public void authorize() {

		String name = outputInputService.requestForInput("Enter your name: ");
		String surname = outputInputService.requestForInput("Enter your surname: ");
		this.authorizedUser = new User(name, surname);

	}

}
