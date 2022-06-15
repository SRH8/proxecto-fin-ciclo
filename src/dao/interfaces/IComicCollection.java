package dao.interfaces;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import model.entities.ComicCollection;
import net.sf.jasperreports.engine.JRResultSetDataSource;

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
	
	/**
	 * Obtiene la respuesta de editar una colecci�n en la base de datos
	 * 
	 * @param clientSocket socket del cliente
	 * @return resultado de la operaci�n
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int editCollection(Socket clientSocket) throws IOException, ClassNotFoundException;
	
	/**
	 * Obtiene el data source que ser� usado en el informe de colecciones
	 * 
	 * @param clientSocket socket del cliente
	 * @return data source
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public JRResultSetDataSource showCollectionReport(Socket clientSocket) throws IOException, ClassNotFoundException;
	
}
