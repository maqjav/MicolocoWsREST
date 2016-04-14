package es.micoloco.ws.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modelo que contiene todos los atributos de una seta
 * 
 * @author Javi
 *
 */
@XmlRootElement
@Entity
public class Seta {

	private Long id;
	private String nombre;
	private String descripcion;
	private Estacion estacion;
	private boolean comestible;
	private boolean confirmada;
	private List<Ubicacion> ubicaciones;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NOMBRE")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "DESCRIPCION")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToOne
	@JoinColumn(name = "IDESTACION")
	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	@Column(name = "COMESTIBLE")
	public boolean isComestible() {
		return comestible;
	}

	public void setComestible(boolean comestible) {
		this.comestible = comestible;
	}

	@Column(name = "CONFIRMADA")
	public boolean isConfirmada() {
		return confirmada;
	}

	public void setConfirmada(boolean confirmada) {
		this.confirmada = confirmada;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "seta", cascade = { CascadeType.REMOVE })
	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
}
