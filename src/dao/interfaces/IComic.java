package dao.interfaces;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import model.entities.Comic;

/**
 * Interface para los cómics
 * 
 * @author Sergio Fraga
 *
 */
public interface IComic {
	
	/**
	 * Lista los cómics de la librería
	 * 
	 * @param clientSocket socket del cliente
	 * @return lista de cómics
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Comic> listComics (Socket clientSocket) throws IOException, ClassNotFoundException;
}
