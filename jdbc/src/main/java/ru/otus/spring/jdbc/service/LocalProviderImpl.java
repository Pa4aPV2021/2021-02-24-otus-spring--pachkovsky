package ru.otus.spring.jdbc.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocalProviderImpl implements LocalProvider {
	private Locale local;

	LocalProviderImpl(@Value("${language}") String language, @Value("${country}") String country) {
		local = new Locale(language, country);
	}

	public Locale getLocal() {
		return this.local;
	}

}
