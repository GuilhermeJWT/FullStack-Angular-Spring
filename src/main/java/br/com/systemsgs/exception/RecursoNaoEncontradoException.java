package br.com.systemsgs.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException() {
		super("Recurso não Encontrado");
	}

}
