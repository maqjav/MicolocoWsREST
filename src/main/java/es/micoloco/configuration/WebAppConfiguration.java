package es.micoloco.configuration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Clase de configuracion del entorno WEB<br/>
 * Esta clase viene a sustituir al fichero web.xml de implementaciones
 * servidoras inferiores a la version 3.0
 * 
 * @author Javi
 *
 */
public class WebAppConfiguration implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) {
		// Inicializacion del ApplicationContext
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

		// Directorio donde se encuentran los ficheros de configuracion de
		// SPRING. Estos ficheros sustituyen al antiguo applicationContext.xml
		context.setConfigLocation("es.micoloco.configuration");

		// ContextLoaderListener
		servletContext.addListener(new ContextLoaderListener(context));

		// CharacterEncodingFilter. Establece el content-type = UTF-8
		// para introducir compatibilidad con todos los navegadores
		FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter("UTF-8", true));
		characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	}
}