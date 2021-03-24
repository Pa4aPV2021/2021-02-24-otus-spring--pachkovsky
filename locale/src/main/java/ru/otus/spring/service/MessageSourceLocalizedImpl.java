package ru.otus.spring.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageSourceLocalizedImpl implements MessageSourceLocalized {
	private final Locale locale;
	private final MessageSource messageSource;

	MessageSourceLocalizedImpl(Locale locale, MessageSource messageSource) {
		this.locale = locale;
		this.messageSource = messageSource;
	}

	@Override
	public String getMessage(String code, String... args) {
		return this.messageSource.getMessage("strings." + code, args, locale);
	}

	@Override
	public String getMessage(String code) {
		return this.messageSource.getMessage("strings." + code, null, locale);
	}

}
