package dao.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import dao.interfaces.IComicCollection;
import model.entities.ComicCollection;

/**
 * 
 * @author Sergio Fraga
 */
public class ComicCollectionDAO implements IComicCollection{

	@Override
	public ArrayList<ComicCollection> listCollections(Socket clientSocket) throws IOException, ClassNotFoundException {
		ObjectInputStream inputObject = new ObjectInputStream(clientSocket.getInputStream());
		//System.out.println("lista cliente " + inputObject.readObject());
		ArrayList<ComicCollection> collectionList = (ArrayList<ComicCollection>) inputObject.readObject();
		
		return collectionList;	
	}
}