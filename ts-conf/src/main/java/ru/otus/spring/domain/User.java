package ru.otus.spring.domain;

public class User {
	
	private int id;
	private String name;
	private String surname;

	public User(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public User() {
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + "]";
	}
}
