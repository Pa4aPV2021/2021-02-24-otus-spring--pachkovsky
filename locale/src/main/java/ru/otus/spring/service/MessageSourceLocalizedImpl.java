package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageSourceLocalizedImpl implements MessageSourceLocalized {
	private final LocalProvader localProvader;
	private final MessageSource messageSource;

	MessageSourceLocalizedImpl(LocalProvader localProvader, MessageSource messageSource) {
		this.localProvader = localProvader;
		this.messageSource = messageSource;
	}

	@Override
	public String getMessage(String code, String... args) {
		return this.messageSource.getMessage("strings." + code, args, localProvader.getLocal());
	}

	@Override
	public String getMessage(String code) {
		return this.messageSource.getMessage("strings." + code, null, localProvader.getLocal());
	}

}
