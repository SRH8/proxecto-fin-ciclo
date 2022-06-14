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
				
				outputToServer.writeObject(command);
				
				serverCommand = dataInputStream.readUTF();
				
		
				switch(serverCommand) {
					case "listarColeccionOK" -> {
						ArrayList<ComicCollection> collectionList = comicCollectionDAO.listCollections(clientSocket);
						table.setModel(new CollectionTableModel(collectionList));				
					}
					case "insertarColeccionOK" -> {
						int result = comicCollectionDAO.insertCollection(clientSocket);
						System.out.println("NUMERO DE FILAS EN EL CLIENTE DESPUES DE INSERTAR " + result);
						if(result > 0) {
							JOptionPane.showMessageDialog(null, "Se ha insertado correctamente", "Insertar colección", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "No se ha podido insertar", "Error", JOptionPane.ERROR_MESSAGE);
						}
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
