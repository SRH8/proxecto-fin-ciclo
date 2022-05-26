package model.entities;

/**
 * Modelo para las colecciones de comics
 * 
 * @author Sergio Fraga
 */
public class ComicCollection {
	
	/**
	 * identificador de la colecci�n
	 */
	private int id;
	
	/**
	 * nombre del a colecci�n
	 */
	private String name;
	
	/**
	 * descripci�n de la colecci�n
	 */
	private String description;
	
	/**
	 * Constructor para crear un objeto colecci�n vac�o
	 */
	public ComicCollection() {}

	/**
	 * Constructor para crear un objeto colecci�n
	 * 
	 * @param id identificador de la colecci�n
	 * @param name nombre de la colecci�n
	 * @param description descripci�n de la colecci�n
	 */
	public ComicCollection(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Obtiene el id de la colecci�n
	 * 
	 * @return id de la colecci�n
	 */
	public int getId() {
		return id;
	}

	/**
	 * Define el id de la colecci�n
	 * 
	 * @param id id de la colecci�n
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre de la colecci�n
	 * 
	 * @return nombre de la colecci�n
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define el nombre de la colecci�n
	 * 
	 * @param name nombre de la colecci�n
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene la descripci�n de la colecci�n
	 * 
	 * @return descripci�n de la colecci�n
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Define la descripci�n de la colecci�n
	 * @param description descripci�n de la colecci�n
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
