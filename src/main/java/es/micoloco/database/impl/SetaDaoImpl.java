package es.micoloco.database.impl;

import org.springframework.stereotype.Repository;

import es.micoloco.database.ISetaDao;
import es.micoloco.ws.model.Seta;

@Repository
public class SetaDaoImpl extends BaseDaoImpl<Seta, Long>implements ISetaDao {

	private static final long serialVersionUID = 142919433405417370L;

}
