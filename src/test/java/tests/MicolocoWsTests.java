package tests;

import java.util.Arrays;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.filter.RequestContextFilter;

import es.micoloco.configuration.ComponentsConfiguration;
import es.micoloco.configuration.DataBaseConfiguration;
import es.micoloco.ws.MicolocoWS;
import es.micoloco.ws.error.GenericExceptionMapper;
import es.micoloco.ws.model.Seta;
import es.micoloco.ws.model.Ubicacion;

/**
 * Clase para tests del servicio web.<br/>
 * Este test levanta un servidor embebido, con lo que no es necesario tener una
 * instancia con el web service corriendo.
 * 
 * @author Javi
 *
 */
public class MicolocoWsTests extends JerseyTest {

	@Override
	protected Application configure() {
		ResourceConfig rc = new ResourceConfig();
		// Establece las configuraciones de Spring
		rc.property("contextConfig", new AnnotationConfigApplicationContext(DataBaseConfiguration.class, ComponentsConfiguration.class));

		// Establece los listener de Spring
		rc.register(SpringLifecycleListener.class).register(RequestContextFilter.class);

		// Establece los ficheros del servicio web REST
		rc.registerClasses(MicolocoWS.class, GenericExceptionMapper.class);

		return rc;
	}

	@Test
	public void insert() {
		Seta seta = new Seta();
		seta.setNombre("seta pruebas");
		Ubicacion ubicacion = new Ubicacion();
		ubicacion.setLatitud("6.2232131");
		ubicacion.setLongitud("67.13123123");
		seta.setUbicaciones(Arrays.asList(ubicacion));

		// En target se define la URL a la que atacar
		Seta respuesta = target("/setas/insertSeta").request().post(Entity.entity(seta, MediaType.APPLICATION_JSON), Seta.class);
		System.out.println(respuesta.getId());
	}

	@Test
	public void getSetas() {
		// En target se define la URL a la que atacar
		Response respuesta = target("/setas/getSetas").request().get();
		System.out.println("STATUS: " + respuesta.getStatus());
	}

	@Test
	public void findSetaById() {
		String id = "1";

		// En target se define la URL a la que atacar
		Response respuesta = target("/setas/findSetaById/" + id).request().get();
		System.out.println("STATUS: " + respuesta.getStatus());
	}
}