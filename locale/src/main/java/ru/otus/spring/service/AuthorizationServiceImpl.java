package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import ru.otus.spring.domain.User;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
	private final OutputInputService outputInputService;
	private final MessageSourceLocalized messageSourceLocalized;

	public AuthorizationServiceImpl(OutputInputService outputInputService,
			MessageSourceLocalized messageSourceLocalized) {
		this.outputInputService = outputInputService;
		this.messageSourceLocalized = messageSourceLocalized;
	}

	public User authorize() {
		String name = outputInputService.requestForInput(messageSourceLocalized.getMessage("in-info.name"));
		String surname = outputInputService.requestForInput(messageSourceLocalized.getMessage("in-info.surname"));
		return new User(name, surname);

	}

}
