package model.entities;

/**
 * Modelo para las colecciones de comics
 * 
 * @author Sergio Fraga
 */
public class ComicCollection {
	
	/**
	 * Identificador de la colección
	 */
	private int id;
	
	/**
	 * Nombre del a colección
	 */
	private String name;
	
	/**
	 * Descripción de la colección
	 */
	private String description;
	
	/**
	 * Constructor para crear un objeto colección vacío
	 */
	public ComicCollection() {}

	/**
	 * Constructor para crear un objeto colección
	 * 
	 * @param id identificador de la colección
	 * @param name nombre de la colección
	 * @param description descripción de la colección
	 */
	public ComicCollection(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Obtiene el id de la colección
	 * 
	 * @return id de la colección
	 */
	public int getId() {
		return id;
	}

	/**
	 * Define el id de la colección
	 * 
	 * @param id id de la colección
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre de la colección
	 * 
	 * @return nombre de la colección
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define el nombre de la colección
	 * 
	 * @param name nombre de la colección
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene la descripción de la colección
	 * 
	 * @return descripción de la colección
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Define la descripción de la colección
	 * @param description descripción de la colección
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}