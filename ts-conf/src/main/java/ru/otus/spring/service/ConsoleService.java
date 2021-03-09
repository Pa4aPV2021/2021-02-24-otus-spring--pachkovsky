package ru.otus.spring.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;


@Service
public class ConsoleService {

	public String requestForInput(String message) {
		String result = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println(message);
		result = scanner.next();
		return result;
	}

	public void requestForOutput(String message, String value) {
		System.out.println(message);
		System.out.println(value);
	}

	public void requestForOutput(String value) {
		System.out.println(value);
	}

}
