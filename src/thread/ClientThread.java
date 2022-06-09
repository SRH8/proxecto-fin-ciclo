package thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.impl.ComicCollectionDAO;
import model.entities.ComicCollection;
import view.model.CollectionTableModel;

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
    private String command;
    
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
	public ClientThread(Socket clientSocket, String command, JTable table) {
		this.clientSocket = clientSocket;
		this.command = command;
		this.table = table;
	}
	
	
    public synchronized void run() {
    	String serverCommand;
    	
    	try {
			if(clientSocket != null) {
				DataOutputStream outputToServer = new DataOutputStream(clientSocket.getOutputStream());
				DataInputStream inputFromServer = new DataInputStream(clientSocket.getInputStream());
				
				outputToServer.writeUTF(command);
				
				serverCommand = inputFromServer.readUTF();
				
		
				switch(serverCommand) {
					case "listarColeccionOK" -> {
						ComicCollectionDAO comicCollectionDAO = new ComicCollectionDAO();
						ArrayList<ComicCollection> collectionList = comicCollectionDAO.listCollections(clientSocket);
						System.out.println(collectionList.get(0).getName());
						table.setModel(new CollectionTableModel(collectionList));				
					}
					case "insertarColeccionOK" -> {
						
					}
					case "borrar" -> {
						
					}
					case "modificar" -> {
						
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
