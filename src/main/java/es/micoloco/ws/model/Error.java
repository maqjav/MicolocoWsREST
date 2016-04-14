package es.micoloco.ws.model;

import es.micoloco.ws.error.GenericExceptionMapper;

/**
 * Clase para la devolucion de errores. <br/>
 * En lugar de devolver un mensaje sin mas
 * con este metodo y la ayuda de {@link GenericExceptionMapper} se devuelven
 * errores con mas detalle.
 * 
 * @author Javi
 *
 */
public class Error {

	private int status;
	private int codigo;
	private String mensaje;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
