package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import model.entities.ComicCollection;

/**
 * Controlador para la pantalla de colecciones
 * 
 * @author Sergio Fraga
 */
public class CollectionWindowController {
	
	/**
	 * Lista las colecciones de la librería
	 * @param clientSocket socket del cliente
	 * @return lista de colecciones
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<ComicCollection> listCollections(Socket clientSocket) throws IOException, ClassNotFoundException {
		ObjectInputStream inputObject = new ObjectInputStream(clientSocket.getInputStream());
		
		List<ComicCollection> collectionList = (List<ComicCollection>) inputObject.readObject();
		
		return collectionList;
		
	}
}
