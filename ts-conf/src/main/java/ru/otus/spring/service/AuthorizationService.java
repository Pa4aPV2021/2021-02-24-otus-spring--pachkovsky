package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import ru.otus.spring.domain.User;

@Service
public class AuthorizationService {

	private User authorizedUser;
	private final ConsoleService consoleService;

	public AuthorizationService(ConsoleService consoleService) {
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
