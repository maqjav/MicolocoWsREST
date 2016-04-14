package es.micoloco.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import es.micoloco.ws.MicolocoWS;
import es.micoloco.ws.error.GenericExceptionMapper;

/**
 * Configuracion del servicio web REST.<br/>
 * En esta clase de configuracion se define la URL que escucha las peticiones y
 * el punto de entrada del servicio web
 * 
 * @author Javi
 *
 */
@ApplicationPath("/*")
@PropertySource(value = { "classpath:configuraciones.properties" })
public class WebServiceConfiguration extends Application {

	private static final Logger LOGGER = Logger.getLogger(WebServiceConfiguration.class.getName());

	/**
	 * Inyecta la variable utilizada para acceder al fichero de configuracion
	 * definido con la anotacion \@PropertySource.
	 */
	@Autowired
	private Environment environment;

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> returnValue = new HashSet<Class<?>>();
		// Se define los proveedores
		returnValue.add(GenericExceptionMapper.class);
		// Se define cual es la clase principal del servicio web
		returnValue.add(MicolocoWS.class);
		return returnValue;
	}

	@Override
	public Set<Object> getSingletons() {
		final Set<Object> singletons = new HashSet<Object>();
		// Configura el logger interno de Jersey
		singletons.add(new LoggingFilter(LOGGER, true));
		return singletons;
	}

	@Override
	public Map<String, Object> getProperties() {
		final Map<String, Object> properties = new HashMap<String, Object>();
		// Habilita las trazas
		properties.put(ServerProperties.TRACING, environment.getProperty("jersey.tracing"));
		return properties;
	}

}
