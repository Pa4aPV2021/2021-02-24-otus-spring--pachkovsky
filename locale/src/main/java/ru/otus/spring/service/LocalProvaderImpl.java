package ru.otus.spring.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocalProvaderImpl implements LocalProvader {
	private Locale local;

	LocalProvaderImpl(@Value("${language}") String language, @Value("${country}") String country) {
		local = new Locale(language, country);
	}

	public Locale getLocal() {
		return this.local;
	}

}
