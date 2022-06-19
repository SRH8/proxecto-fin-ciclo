package dao.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import dao.interfaces.IComic;
import model.entities.Comic;

public class ComicDAO implements IComic {

	@Override
	public ArrayList<Comic> listComics(Socket clientSocket) throws IOException, ClassNotFoundException {
		ObjectInputStream inputObject = new ObjectInputStream(clientSocket.getInputStream());
		
		ArrayList<Comic> comicList = (ArrayList<Comic>) inputObject.readObject();
		
		return comicList;	
	}

	@Override
	public int deleteComic(Socket clientSocket) throws IOException, ClassNotFoundException {
		ObjectInputStream inputObject = new ObjectInputStream(clientSocket.getInputStream());
		int result =  inputObject.readInt();
		
		return result;	
	}

}
