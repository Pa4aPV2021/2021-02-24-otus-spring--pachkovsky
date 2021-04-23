package ru.otus.spring.jdbc.service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class OutputInputServiceImpl implements OutputInputService {

	private final InputStream inputStream;
	private final PrintStream printStream;

	public OutputInputServiceImpl(InputStream inputStream, PrintStream printStream) {
		this.inputStream = inputStream;
		this.printStream = printStream;
	}

	public String requestForInput(String message) {
		printStream.println(message);
		return extracted().next();
	}

	private Scanner extracted() {
		return new Scanner(inputStream);
	}

	public void requestForOutput(String message, String value) {
		printStream.println(message);
		printStream.println(value);
	}

	public void requestForOutput(String value) {
		printStream.println(value);
	}

}
