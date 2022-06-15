package dao.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import dao.interfaces.IComicCollection;
import model.entities.ComicCollection;
import net.sf.jasperreports.engine.JRResultSetDataSource;

/**
 * Implementación del ComicCollectionDAO
 * 
 * @author Sergio Fraga
 */
public class ComicCollectionDAO implements IComicCollection{

	@Override
	public ArrayList<ComicCollection> listCollections(Socket clientSocket) throws IOException, ClassNotFoundException {
		ObjectInputStream inputObject = new ObjectInputStream(clientSocket.getInputStream());
		
		ArrayList<ComicCollection> collectionList = (ArrayList<ComicCollection>) inputObject.readObject();
		
		return collectionList;	
	}

	@Override
	public int insertCollection(Socket clientSocket) throws IOException, ClassNotFoundException {
		ObjectInputStream inputObject = new ObjectInputStream(clientSocket.getInputStream());
		int result =  inputObject.readInt();
		
		return result;
	}

	@Override
	public int deleteCollection(Socket clientSocket) throws IOException, ClassNotFoundException {
		ObjectInputStream inputObject = new ObjectInputStream(clientSocket.getInputStream());
		int result =  inputObject.readInt();
		
		return result;	
	}

	@Override
	public int editCollection(Socket clientSocket) throws IOException, ClassNotFoundException {
		ObjectInputStream inputObject = new ObjectInputStream(clientSocket.getInputStream());
		int result =  inputObject.readInt();
		
		return result;	
	}

	@Override
	public JRResultSetDataSource showCollectionReport(Socket clientSocket) throws IOException, ClassNotFoundException {
		ObjectInputStream inputObject = new ObjectInputStream(clientSocket.getInputStream());
		
		JRResultSetDataSource ds = (JRResultSetDataSource) inputObject.readObject();
		
		return ds;
	}
}