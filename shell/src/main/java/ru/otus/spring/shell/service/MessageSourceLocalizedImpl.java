package ru.otus.spring.shell.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageSourceLocalizedImpl implements MessageSourceLocalized {
	private final LocalProvider localProvider;
	private final MessageSource messageSource;

	MessageSourceLocalizedImpl(LocalProvider localProvider, MessageSource messageSource) {
		this.localProvider = localProvider;
		this.messageSource = messageSource;
	}

	@Override
	public String getMessage(String code, String... args) {
		return this.messageSource.getMessage("strings." + code, args, localProvider.getLocal());
	}

	@Override
	public String getMessage(String code) {
		return this.messageSource.getMessage("strings." + code, null, localProvider.getLocal());
	}

}
