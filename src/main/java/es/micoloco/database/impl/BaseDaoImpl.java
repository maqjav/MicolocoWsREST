package es.micoloco.database.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.transaction.annotation.Transactional;

import es.micoloco.database.IBaseDao;

public class BaseDaoImpl<T, PK extends Serializable> implements IBaseDao<T, PK>, Serializable {

	private static final long serialVersionUID = 1L;

	protected final Log log = LogFactory.getLog(this.getClass());

	protected final Class<T> persistentClass;

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.persistentClass = (Class<T>) GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseDaoImpl.class)[0];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public T get(PK id) {
		return this.entityManager.find(this.persistentClass, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		final StringBuilder sql = new StringBuilder("from ").append(this.persistentClass.getName()).append(" a ");
		final Query query = this.entityManager.createQuery(sql.toString());
		return query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public T insert(T t) {
		this.entityManager.persist(t);
		this.entityManager.flush();
		return t;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public T update(T t) {
		return this.entityManager.merge(t);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(PK id) {
		T t = this.get(id);
		if (t != null) {
			this.entityManager.remove(t);
		}
	}
}