package es.micoloco.ws.error;

public class ErrorException extends RuntimeException {

	private static final long serialVersionUID = -787816780812915319L;

	/**
	 * Codigo interno asociado al error
	 */
	private int codigo;

	public ErrorException(String message, Throwable e, int codigo) {
		super(message, e);
		this.codigo = codigo;
	}

	public ErrorException(String message, int codigo) {
		super(message);
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}
}
