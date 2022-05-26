package model.entities;

import java.util.Date;

/**
 * Modelo de los comics de la librería
 * 
 * @author Sergio Fraga
 */
public class Comic {
	
	/**
	 * Identificador del cómic
	 */
	private int id;
	
	/**
	 * Título del cómic
	 */
	private String title;
	
	/**
	 * Descripción del cómic
	 */
	private String description;
	
	/**
	 * Fecha de estreno del cómic
	 */
	private Date releaseDate;
	
	/**
	 * Tipo de tapa del cómic
	 */
	private CoverType cover;
	
	/**
	 * Imagen de portada del cómic
	 */
	private Byte[] coverImage;
	
	/**
	 * Colección a la que pertenece el cómic
	 */
	private ComicCollection collection;
	
	/**
	 * Estado del cómic
	 */
	private ComicStatus status;
	
	/**
	 * Constructor para crear un objeto cómic vacío
	 */
	public Comic() {}

	/**
	 * Constructor para crear un objeto cómic
	 * @param id identificador del cómic
	 * @param title título del cómic
	 * @param description descripción del cómic
	 * @param releaseDate fecha de estreno del cómic
	 * @param cover tipo de tapa del cómic
	 * @param coverImage imagen de portada del cómic
	 * @param collection colección a la que pertenece el cómic
	 * @param status estado en el que se encuentra el cómic
	 */
	public Comic(int id, String title, String description, Date releaseDate, CoverType cover, Byte[] coverImage,
			ComicCollection collection, ComicStatus status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseDate = releaseDate;
		this.cover = cover;
		this.coverImage = coverImage;
		this.collection = collection;
		this.status = status;
	}

	/**
	 * Obtiene el id del cómic
	 * 
	 * @return id del cómic
	 */
	public int getId() {
		return id;
	}

	/**
	 * Define el identificador del cómic
	 * 
	 * @param id identificador del cómic
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el título del cómic
	 * 
	 * @return título del cómic
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Define el título del cómic
	 * 
	 * @param title título del cómic
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Obtiene la descripción del cómic
	 * 
	 * @return descripción del cómic
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Define la descripción del cómic
	 * 
	 * @param description descripción del cómic
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obtiene la fecha de estreno del cómic
	 * 
	 * @return fecha de estreno del cómic
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Define la fecha de estreno del cómic
	 * 
	 * @param releaseDate
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * Obtiene el tipo de portada del cómic
	 * 
	 * @return tipo de portada del cómic
	 */
	public CoverType getCover() {
		return cover;
	}

	/**
	 * Define el tipo de la tapa del cómic
	 * 
	 * @param cover tipo de la tapa
	 */
	public void setCover(CoverType cover) {
		this.cover = cover;
	}

	/**
	 * Obtiene la imagen de la portada del cómic
	 * 
	 * @return imagen de la portada
	 */
	public Byte[] getCoverImage() {
		return coverImage;
	}

	/**
	 * Define la imagen de la portada del cómic
	 * 
	 * @param coverImage imagen
	 */
	public void setCoverImage(Byte[] coverImage) {
		this.coverImage = coverImage;
	}

	/**
	 * Obtiene la colección a la que pertenece el cómic
	 * 
	 * @return colección a la que pertenece el cómic
	 */
	public ComicCollection getCollection() {
		return collection;
	}

	/**
	 * Define la colección a la que pertenece el cómic
	 * 
	 * @param collection colección
	 */
	public void setCollection(ComicCollection collection) {
		this.collection = collection;
	}

	/**
	 * Obtiene el estado en el que se encuentra el cómic
	 * 
	 * @return estado en el que se encuentra el cómic
	 */
	public ComicStatus getStatus() {
		return status;
	}

	/**
	 * Define el estado en el que se encuentra el cómic
	 * 
	 * @param status estado del cómic
	 */
	public void setStatus(ComicStatus status) {
		this.status = status;
	}
	
}