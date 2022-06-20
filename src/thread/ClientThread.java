package thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import controller.ComicCollectionController;
import controller.ComicController;
import dao.impl.ComicCollectionDAO;
import dao.impl.ComicDAO;
import model.entities.Comic;
import model.entities.ComicCollection;
import model.entities.ComicStatus;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import view.CollectionWindow;
import view.ComicWindow;
import view.MainWindow;
import view.model.CollectionTableModel;
import view.model.ComicTableModel;

/**
 * Hilo del cliente
 * 
 * @author Sergio Fraga
 */
public class ClientThread extends Thread {
	
	/**
	 * Socket del cliente
	 */
	private Socket clientSocket;
	
	/**
	 * Orden que recibe el hilo
	 */
    private Object[] command;
    
    /**
     * Tabla que utiliza el thread
     */
    private JTable table;
    
    /**
     * Crea un nuevo thread
     * 
     * @param clientSocket socket del cliente
     * @param command orden que recibe
     * @param table tabla que utiliza el thread
     */
	public ClientThread(Socket clientSocket, Object[] command, JTable table) {
		this.clientSocket = clientSocket;
		this.command = command;
		this.table = table;
	}
	
	
    public synchronized void run() {
    	String serverCommand;
    	
    	try {
			if(clientSocket != null) {
				ObjectOutputStream outputToServer = new ObjectOutputStream(clientSocket.getOutputStream());
				DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
				ComicCollectionDAO comicCollectionDAO = new ComicCollectionDAO();
				ComicDAO comicDAO = new ComicDAO();
				ResourceBundle rb = ResourceBundle.getBundle(MainWindow.language);
				
				outputToServer.writeObject(command);
				
				serverCommand = dataInputStream.readUTF();
				
		
				switch(serverCommand) {
					case "listarColeccionOK" -> {
						ArrayList<ComicCollection> collectionList = comicCollectionDAO.listCollections(clientSocket);
						table.setModel(new CollectionTableModel(collectionList));				
						CollectionWindow.collectionList = collectionList;
						ComicCollectionController collectionController = new ComicCollectionController();
						collectionController.traducirTable(MainWindow.language, table);
					}
					case "insertarColeccionOK" -> {
						int result = comicCollectionDAO.insertCollection(clientSocket);
			
						if(result > 0) {
							JOptionPane.showMessageDialog(null, rb.getString("msgInsert"), rb.getString("btnInsert") + " colección", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, rb.getString("msgInsertFail"), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					case "eliminarColeccionOK" -> {
						int result = comicCollectionDAO.deleteCollection(clientSocket);
						
						if(result > 0) {
							JOptionPane.showMessageDialog(null, rb.getString("msgDelete"), "Eliminar colección", JOptionPane.INFORMATION_MESSAGE);
						} else {
						JOptionPane.showMessageDialog(null, rb.getString("msgDeleteFail"), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					case "editarColeccionOK" -> {
						int result = comicCollectionDAO.editCollection(clientSocket);
						
						if(result > 0) {
							JOptionPane.showMessageDialog(null, rb.getString("msgEdit"), "Editar colección", JOptionPane.INFORMATION_MESSAGE);
						} else {
						JOptionPane.showMessageDialog(null, rb.getString("msgEditFail"), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					case "listarComicsOK" -> {
						ArrayList<Comic> comicList = comicDAO.listComics(clientSocket);
						table.setModel(new ComicTableModel(comicList));				
						ComicWindow.comicList = comicList;
						ComicController comicController = new ComicController();
						comicController.traducirTable(MainWindow.language, table);
					}
					case "eliminarComicOK" -> {
						int result = comicDAO.deleteComic(clientSocket);
						
						if(result > 0) {
							JOptionPane.showMessageDialog(null,  rb.getString("msgDelete"), "Eliminar cómic", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, rb.getString("msgDeleteFail"), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					case "buscarComicPorNombreOK" -> {
						ArrayList<Comic> comicList = comicDAO.searchComicByName(clientSocket);
						table.setModel(new ComicTableModel(comicList));		
						ComicController comicController = new ComicController();
						comicController.traducirTable(MainWindow.language, table);
					}
					case "buscarComicsPorColeccionOK" -> {
						ArrayList<Comic> comicList = comicDAO.searchComicByCollection(clientSocket);
						table.setModel(new ComicTableModel(comicList));	
						ComicController comicController = new ComicController();
						comicController.traducirTable(MainWindow.language, table);
					}
					case "insertarComicOK" -> {
						int result = comicDAO.insertComic(clientSocket);
						
						if(result > 0) {
							JOptionPane.showMessageDialog(null, rb.getString("msgInsert"), rb.getString("btnInsert") + " cómic", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, rb.getString("msgInsertFail"), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					case "editarComicOK" -> {
						int result = comicDAO.editComic(clientSocket);
						
						if(result > 0) {
							JOptionPane.showMessageDialog(null, rb.getString("msgEdit"), "Editar cómic", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, rb.getString("msgEditFail"), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Fallo de conexión", "Cliente", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ConnectException connectException) {
            connectException.printStackTrace();
        } catch (IOException ioException) {
           ioException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
