package ru.otus.spring.shell.service;

public interface MessageSourceLocalized {

	String getMessage(String code, String... args);

	String getMessage(String code);

}
