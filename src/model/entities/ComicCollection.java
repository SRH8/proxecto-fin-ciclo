package model.entities;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Modelo para las colecciones de comics
 * 
 * @author Sergio Fraga
 */
public class ComicCollection implements Serializable{

	/**
	 * Identificador único de la clase
	 */
	private static final long serialVersionUID = 6529685098267757690L;
	
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
	 * Imagen de la colección
	 */
	private byte[] image;
	
	/**
	 * Año en que salió el primer cómic de la colección
	 */
	private String releaseYear;
	
	/**
	 * Constructor para crear un objeto colección vacío
	 */
	public ComicCollection() {}

	/**
	 * Constructor para crear un objeto colección
	 * 
	 * @param name nombre de la colección
	 * @param description descripción de la colección
	 * @param image imagen de la colección
	 * @param releaseYear año de estreno
	 */
	public ComicCollection(String name, String description, byte[] image, String releaseYear) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.releaseYear = releaseYear;
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
	 * 
	 * @param description descripción de la colección
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Obtiene la imagen de la colección
	 * 
	 * @return imagen de la colección
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * Define la imagen de la colección
	 * 
	 * @param image imagen de la colección
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	/**
	 * Obtiene el año de estreno de la colección
	 * 
	 * @return año de estreno
	 */
	public String getReleaseYear() {
		return releaseYear;
	}

	/**
	 * Define el año de estreno de la colección
	 * 
	 * @param releaseYear año de estreno
	 */
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	@Override
	public String toString() {
		return "ComicCollection [id=" + id + ", name=" + name + ", description=" + description + ", image="
				+ Arrays.toString(image) + ", releaseYear=" + releaseYear + "]";
	}
}