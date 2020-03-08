package br.com.kek.meufilme.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(RegistroNaoEncontradoException.class)
	public String handleNotFoundException(RegistroNaoEncontradoException ex) {
		return ex.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(GeneroNaoCadastradoException.class)
	public String handleGeneroNaoCadastradoException(GeneroNaoCadastradoException ex) {
		return ex.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UpdateRegistroSemIdentificadorException.class)
	public String handleUpdateRegistroSemIdentificadorException(UpdateRegistroSemIdentificadorException ex) {
		return ex.getMessage();
	}

}
