package es.micoloco.database;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T, PK extends Serializable> {

	/**
	 * Obtiene una objeto dado su identificador
	 *
	 * @param identificador
	 *            Clave primaria del objeto
	 */
	public T get(PK identificador);

	/**
	 * Obtiene la lista completa de objetos
	 *
	 * @return Lista de objetos
	 */
	public List<T> getAll();

	/**
	 * Inserta un objeto en base de datos
	 *
	 * @param objeto
	 *            Objeto a insertar
	 * @return Objeto recuperado tras la inserccion
	 */
	public T insert(T t);

	/**
	 * Actualiza un objeto en base de datos
	 *
	 * @param objeto
	 *            Objeto a actualizar
	 * @return Objeto recuperado tras la actualizacion
	 */
	public T update(T objeto);

	/**
	 * Elimina un objeto de la base de datos dado su identificador
	 *
	 * @param identificador
	 *            Clave primaria del objeto
	 */
	public void delete(PK identificador);
}
