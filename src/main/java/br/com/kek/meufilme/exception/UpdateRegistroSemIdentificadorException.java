package br.com.kek.meufilme.exception;

import br.com.kek.meufilme.utils.ExtrairMensagemUtil;

/**
 * @author Kelisson.Leite
 *
 */
public class UpdateRegistroSemIdentificadorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param msg
	 */
	public UpdateRegistroSemIdentificadorException(String msg) {
		super(ExtrairMensagemUtil.extrairMensagem(msg));
    }

    /**
     * @param msg
     * @param e
     */
    public UpdateRegistroSemIdentificadorException(String msg, Exception e) {
    	super(ExtrairMensagemUtil.extrairMensagem(msg) + " because of " + e.toString());
    }

}
