package es.micoloco.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuracion de componentes...<br/>
 * En esta clase se definen los scanner para localizar las anotaciones
 * /@Component, /@Service, /@Repository, etc.<br/>
 * Aqui tambien se pueden instanciar otros Beans de uso general.
 * 
 * @author Javi
 *
 */
@Configuration
@ComponentScan({ "es.micoloco.database", "es.micoloco.ws" })
public class ComponentsConfiguration {

	// Aqui se pueden definir otros beans que sean de uso general para toda la
	// aplicacion
}
