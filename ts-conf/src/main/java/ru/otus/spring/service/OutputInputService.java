package ru.otus.spring.service;

public interface ConsoleService {

	public String requestForInput(String message);

	public void requestForOutput(String message, String value);

	public void requestForOutput(String value);

}
