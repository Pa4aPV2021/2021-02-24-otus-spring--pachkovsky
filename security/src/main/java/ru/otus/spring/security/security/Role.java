package ru.otus.spring.security.security;

public enum Role {
	USER("user");

	public final String label;

	private Role(String label) {
		this.label = label;
	}

}
