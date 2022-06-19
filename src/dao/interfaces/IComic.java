package dao.interfaces;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import model.entities.Comic;

/**
 * Interface para los c�mics
 * 
 * @author Sergio Fraga
 *
 */
public interface IComic {
	
	/**
	 * Lista los c�mics de la librer�a
	 * 
	 * @param clientSocket socket del cliente
	 * @return lista de c�mics
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Comic> listComics (Socket clientSocket) throws IOException, ClassNotFoundException;
	
	/**
	 * Elimina un c�mic de la librer�a
	 * 
	 * @param clientSocket socket del cliente
	 * @return resultado de la operaci�n
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int deleteComic(Socket clientSocket) throws IOException, ClassNotFoundException;
	
	/**
	 * Busca un c�mic por nombre
	 * 
	 * @param clientSocket socket del cliente
	 * @return lista de c�mics
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Comic> searchComicByName(Socket clientSocket) throws IOException, ClassNotFoundException;
}
