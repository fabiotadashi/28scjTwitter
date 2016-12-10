package br.com.fiap.twitter.exception;


/**
 * Classe de excecao para conexao do twitter
 * @author FabioMiyasato
 *
 */
public class ConexaoTwitterException extends Exception {
	
	private static final long serialVersionUID = 1L;

		
	public ConexaoTwitterException(String mensagem) {
		super(mensagem);
	}

}
