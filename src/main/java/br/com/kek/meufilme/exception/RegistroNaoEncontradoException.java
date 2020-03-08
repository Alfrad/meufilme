package br.com.kek.meufilme.exception;

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
        super(msg);
    }

    /**
     * @param msg
     * @param e
     */
    public RegistroNaoEncontradoException(String msg, Exception e) {
        super(msg + " because of " + e.toString());
    }

}
