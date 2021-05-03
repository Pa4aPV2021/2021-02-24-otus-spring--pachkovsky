package ru.otus.spring.jpa.service;

public interface MessageSourceLocalized {

	String getMessage(String code, String... args);

	String getMessage(String code);

}
