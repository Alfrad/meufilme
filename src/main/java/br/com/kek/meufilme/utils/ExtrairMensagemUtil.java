package br.com.kek.meufilme.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ExtrairMensagemUtil {

	@Autowired
	private MessageSource messageSource;

	private static ExtrairMensagemUtil utils;

	@PostConstruct
	public void init() {
		utils = this;
		utils.messageSource = this.messageSource;
	}

	public static String extrairMensagem(String valor) {
		return utils.messageSource.getMessage(valor, null, LocaleContextHolder.getLocale());
	}

}
