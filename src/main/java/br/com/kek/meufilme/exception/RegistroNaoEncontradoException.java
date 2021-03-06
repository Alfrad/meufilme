package br.com.kek.meufilme.exception;

import br.com.kek.meufilme.utils.ExtrairMensagemUtil;

/**
 * @author Kelisson.Leite
 *
 */
public class RegistroNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param msg
	 */
	public RegistroNaoEncontradoException(String msg) {
		super(ExtrairMensagemUtil.extrairMensagem(msg));
    }

    /**
     * @param msg
     * @param e
     */
    public RegistroNaoEncontradoException(String msg, Exception e) {
    	super(ExtrairMensagemUtil.extrairMensagem(msg) + " because of " + e.toString());
    }

}
