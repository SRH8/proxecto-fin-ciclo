package model.entities;

import java.io.Serializable;

/**
 * Modelo para el estado del comic
 * 
 * @author Sergio Fraga
 */
public class ComicStatus implements Serializable{
	
	/**
	 * Identificador único de la clase
	 */
	private static final long serialVersionUID = 6529685098267757692L;

	/**
	 * Identificador del estado
	 */
	private int id;
	
	/**
	 * Descripción del estado
	 */
	private String description;
	
	/**
	 * Constructor para crear un objeto estado vacío
	 */
	public ComicStatus() {}

	/**
	 * Constructor para crear un objeto estado
	 * 
	 * @param id identificador del estado
	 * @param description descripción del estado
	 */
	public ComicStatus(int id, String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Obtiene el id del estado
	 * 
	 * @return identificador del estado
	 */
	public int getId() {
		return id;
	}

	/**
	 * Define el id del estado
	 * 
	 * @param id identificador del estado
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene la descripción del estado
	 * 
	 * @return descripción del estado
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Define la descripción del estado
	 * 
	 * @param description descripción del estado
	 */
	public void setDescription(String description) {
		this.description = description;
	}	
	
}