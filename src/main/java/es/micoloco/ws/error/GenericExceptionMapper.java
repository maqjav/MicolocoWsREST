package es.micoloco.ws.error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.micoloco.ws.model.Error;

/**
 * Clase para la captura de excepciones y formado del mensaje de error.
 * Parece no funcionar en version 2.5.1
 * 
 * @author Javi
 *
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<ErrorException> {

	@Override
	public Response toResponse(ErrorException ex) {
		Error errorMessage = new Error();
		setHttpStatus(ex, errorMessage);
		errorMessage.setMensaje(ex.getMessage());
		errorMessage.setCodigo(ex.getCodigo());
		return Response.status(errorMessage.getStatus()).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
	}

	private void setHttpStatus(Throwable ex, Error errorMessage) {
		if (ex instanceof WebApplicationException) {
			errorMessage.setStatus(((WebApplicationException) ex).getResponse().getStatus());
		} else {
			errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}
	}
}