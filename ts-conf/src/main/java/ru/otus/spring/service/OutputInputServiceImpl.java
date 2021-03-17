package ru.otus.spring.service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsoleServiceImpl implements ConsoleService {

	InputStream inputStream = System.in;
	PrintStream printStream = System.out;

	@Autowired
	ConsoleServiceImpl(InputStream inputStream, PrintStream printStream) {
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
