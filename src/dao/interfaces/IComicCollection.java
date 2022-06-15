package dao.interfaces;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import model.entities.ComicCollection;

/**
 * Interface para las colecciones de c�mics
 * 
 * @author Sergio Fraga
 */
public interface IComicCollection {
	
	/**
	 * Lista las colecciones de la librer�a
	 * 
	 * @param clientSocket socket del cliente
	 * @return lista de colecciones
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<ComicCollection> listCollections (Socket clientSocket) throws IOException, ClassNotFoundException;
	
	/**
	 * Obtiene la respuesta de insertar una colecci�n en la base de datos
	 * 
	 * @param clientSocket socket del cliente
	 * @return resultado de la operaci�n
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int insertCollection(Socket clientSocket) throws IOException, ClassNotFoundException;
	
	
	/**
	 * Obtiene la respuesta de eliminar una colecci�n en la base de datos
	 * 
	 * @param clientSocket socket del cliente
	 * @return resultado de la operaci�n
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int deleteCollection(Socket clientSocket) throws IOException, ClassNotFoundException;
	
}
