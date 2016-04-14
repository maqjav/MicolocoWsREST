package es.micoloco.database.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.micoloco.database.IUbicacionDao;
import es.micoloco.ws.model.Seta;
import es.micoloco.ws.model.Ubicacion;

@Repository
public class UbicacionDaoImpl extends BaseDaoImpl<Ubicacion, Long>implements IUbicacionDao {

	private static final long serialVersionUID = 142919433405417370L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Ubicacion> getBySeta(Seta seta) {
		final StringBuilder sql = new StringBuilder("from ").append(this.persistentClass.getName()).append(" a ");
		sql.append("where a.seta = :seta");

		final Query query = this.entityManager.createQuery(sql.toString());
		query.setParameter("seta", seta);
		return query.getResultList();
	}

}
