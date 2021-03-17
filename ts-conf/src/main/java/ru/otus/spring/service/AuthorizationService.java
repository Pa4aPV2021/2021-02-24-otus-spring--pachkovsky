package ru.otus.spring.service;

import ru.otus.spring.domain.User;

public interface AuthorizationService {

	public User getAuthorizedUser();

	public void authorize();
}
