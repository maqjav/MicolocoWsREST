
package es.micoloco.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.micoloco.database.ISetaDao;
import es.micoloco.database.IUbicacionDao;
import es.micoloco.ws.error.ErrorException;
import es.micoloco.ws.model.Seta;

/**
 * Servicio web rest para consultas sobre setas
 * 
 * @author Javi
 *
 */
@Path("/setas")
@Component
public class MicolocoWS {

	private static final Logger logger = LogManager.getLogger(MicolocoWS.class);

	@Autowired
	private ISetaDao setaDao;

	@Autowired
	private IUbicacionDao ubicacionDao;

	/**
	 * Obtiene el listado completo de setas
	 * 
	 * @return
	 */
	@GET
	@Path("/getSetas")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSetas() {
		List<Seta> setas = null;
		try {
			setas = setaDao.getAll();
			if (setas != null && !setas.isEmpty()) {
				setas.stream().forEach(e -> e.setUbicaciones(ubicacionDao.getBySeta(e)));
			}
		} catch (Exception e) {
			logger.error("Se ha producido un error al obtener el listado completo de setas", e);
			throw new ErrorException("Error de base de datos", -1);
		}

		// No se puede dejar en manos de hiberante la carga de las
		// ubicaciones porque el objeto guardado en setUbicaciones es un proxy
		// que produce una excepcion en el serializador de JSON.

		// Dado que esta consulta seria mas ligera con un JOIN es posible
		// que sea mas interesante hacerla directamente con JDBC o devolver un
		// objeto auxiliar en lugar del propio pojo

		// De momento lanzamos otra consulta por cada seta para sacar sus
		// ubicaciones y que se muestren en el JSON de ejemplo
		if (setas != null && !setas.isEmpty()) {
			GenericEntity<List<Seta>> entity = new GenericEntity<List<Seta>>(setas) {
			};
			return Response.ok(entity).build();
		}

		logger.info("El metodo getSetas no ha devuelto datos");
		throw new ErrorException("El metodo getSetas no ha devuelto datos", 1);
	}

	/**
	 * Obtiene una seta concreta dado su identificador
	 * 
	 * @return
	 */
	@GET
	@Path("/findSetaById/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findSetaById(@PathParam("id") Long id) {
		Seta seta = null;
		try {
			seta = setaDao.get(id);
			if (seta != null) {
				seta.setUbicaciones(ubicacionDao.getBySeta(seta));
			}
		} catch (Exception e) {
			logger.error("Se ha producido un error al buscar una seta", e);
			throw new ErrorException("Error de base de datos", -1);
		}

		if (seta != null) {
			GenericEntity<Seta> entity = new GenericEntity<Seta>(seta) {
			};
			return Response.ok(entity).build();
		}

		logger.info("El metodo findSetaById no ha devuelto datos para el ID : " + id);
		throw new ErrorException("El metodo findSetaById no ha devuelto datos para el ID : " + id, 1);
	}

	/**
	 * Inserta una nueva seta
	 * 
	 * @return
	 */
	@POST
	@Path("/insertSeta")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response insertSeta(Seta seta) {
		try {
			final Seta setaNueva = setaDao.insert(seta);

			// Si existen ubicaciones se insertan tambien
			if (seta.getUbicaciones() != null) {
				seta.getUbicaciones().stream().peek(e -> e.setSeta(setaNueva)).forEach(e -> e = ubicacionDao.insert(e));
			}

			if (setaNueva != null) {
				GenericEntity<Seta> entity = new GenericEntity<Seta>(setaNueva) {
				};
				return Response.ok(entity).build();
			}
		} catch (Exception e) {
			logger.error("Se ha producido un error al insertar una seta", e);
			throw new ErrorException("Error de base de datos", -1);
		}

		throw new ErrorException("No se ha insertado la nueva seta correctamente", 1);
	}

	/**
	 * Actualiza los datos de una seta existente
	 * 
	 * @return
	 */
	@POST
	@Path("/updateSeta")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateSeta(Seta seta) {
		Seta setaActualizada = null;
		try {
			setaActualizada = setaDao.update(seta);
		} catch (Exception e) {
			logger.error("Se ha producido un error al actualizar una seta", e);
			throw new ErrorException("Error de base de datos", -1);
		}

		if (setaActualizada != null) {
			GenericEntity<Seta> entity = new GenericEntity<Seta>(setaActualizada) {
			};
			return Response.ok(entity).build();
		}

		throw new ErrorException("No se ha actualizado la seta correctamente", 1);
	}

	/**
	 * Borra una seta existente
	 * 
	 * @return
	 */
	@GET
	@Path("/deleteSeta/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteSeta(@PathParam("id") Long id) {
		try {
			setaDao.delete(id);
		} catch (Exception e) {
			logger.error("Se ha producido un error al intentar borrar una seta", e);
			throw new ErrorException("Error de base de datos", -1);
		}

		return Response.ok().build();
	}
}
