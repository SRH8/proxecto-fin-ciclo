package dao.interfaces;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import model.entities.ComicCollection;

/**
 * Interface para las colecciones de cómics
 * 
 * @author Sergio Fraga
 */
public interface IComicCollection {
	
	/**
	 * Lista las colecciones de la librería
	 * 
	 * @param clientSocket socket del cliente
	 * @return lista de colecciones
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<ComicCollection> listCollections (Socket clientSocket) throws IOException, ClassNotFoundException;
	
	/**
	 * Obtiene la respuesta de insertar una colección en la base de datos
	 * 
	 * @param clientSocket socket del cliente
	 * @return resultado de la operación
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int insertCollection(Socket clientSocket) throws IOException, ClassNotFoundException;
	
	
	/**
	 * Obtiene la respuesta de eliminar una colección en la base de datos
	 * 
	 * @param clientSocket socket del cliente
	 * @return resultado de la operación
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int deleteCollection(Socket clientSocket) throws IOException, ClassNotFoundException;
	
}
