package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import ru.otus.spring.domain.User;

@Service
public class AuthorizationServiceImpl implements AuthorizationService  {

	private User authorizedUser;
	private final ConsoleServiceImpl consoleService;

	public AuthorizationServiceImpl(ConsoleServiceImpl consoleService) {
		this.consoleService = consoleService;
	}

	public User getAuthorizedUser() {
		return authorizedUser;
	}

	public void authorize() {

		String name = consoleService.requestForInput("Enter your name: ");
		String surname = consoleService.requestForInput("Enter your surname: ");
		this.authorizedUser = new User(name, surname);

	}

}
