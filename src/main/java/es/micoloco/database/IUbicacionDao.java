package es.micoloco.database;

import java.util.List;

import es.micoloco.ws.model.Seta;
import es.micoloco.ws.model.Ubicacion;

public interface IUbicacionDao extends IBaseDao<Ubicacion, Long> {

	/**
	 * Obtiene las ubicaciones de una seta concreta
	 * 
	 * @param seta
	 * @return
	 */
	public List<Ubicacion> getBySeta(Seta seta);
}
