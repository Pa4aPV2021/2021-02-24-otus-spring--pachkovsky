package ru.otus.spring.shell.service;

public interface OutputInputService {

	public String requestForInput(String message);

	public void requestForOutput(String message, String value);

	public void requestForOutput(String value);

}
