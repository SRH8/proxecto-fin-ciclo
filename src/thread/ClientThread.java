package thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import dao.impl.ComicCollectionDAO;
import dao.impl.ComicDAO;
import model.entities.Comic;
import model.entities.ComicCollection;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import view.CollectionWindow;
import view.ComicWindow;
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
				
				outputToServer.writeObject(command);
				
				serverCommand = dataInputStream.readUTF();
				
		
				switch(serverCommand) {
					case "listarColeccionOK" -> {
						ArrayList<ComicCollection> collectionList = comicCollectionDAO.listCollections(clientSocket);
						table.setModel(new CollectionTableModel(collectionList));				
						CollectionWindow.collectionList = collectionList;
					}
					case "insertarColeccionOK" -> {
						int result = comicCollectionDAO.insertCollection(clientSocket);
			
						if(result > 0) {
							JOptionPane.showMessageDialog(null, "Se ha insertado correctamente", "Insertar colecci�n", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "No se ha podido insertar", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					case "eliminarColeccionOK" -> {
						int result = comicCollectionDAO.deleteCollection(clientSocket);
						
						if(result > 0) {
							JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente", "Eliminar colecci�n", JOptionPane.INFORMATION_MESSAGE);
						} else {
						JOptionPane.showMessageDialog(null, "No se ha podido eliminar", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					case "editarColeccionOK" -> {
						int result = comicCollectionDAO.editCollection(clientSocket);
						
						if(result > 0) {
							JOptionPane.showMessageDialog(null, "Se ha editado correctamente", "Editar colecci�n", JOptionPane.INFORMATION_MESSAGE);
						} else {
						JOptionPane.showMessageDialog(null, "No se ha podido editar", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					case "informeColeccionesOK" -> {
						JRResultSetDataSource ds = comicCollectionDAO.showCollectionReport(clientSocket);
						CollectionWindow.dataSource = ds;
					}
					case "listarComicsOK" -> {
						ArrayList<Comic> comicList = comicDAO.listComics(clientSocket);
						table.setModel(new ComicTableModel(comicList));				
						ComicWindow.comicList = comicList;
					}
					case "eliminarComicOK" -> {
						int result = comicDAO.deleteComic(clientSocket);
						
						if(result > 0) {
							JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente", "Eliminar c�mic", JOptionPane.INFORMATION_MESSAGE);
						} else {
						JOptionPane.showMessageDialog(null, "No se ha podido eliminar", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Fallo de conexi�n", "Cliente", JOptionPane.ERROR_MESSAGE);
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
