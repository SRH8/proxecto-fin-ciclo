package thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import javax.swing.JOptionPane;

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
     * Crea un nuevo thread
     * 
     * @param clientSocket socket del cliente
     * @param command orden que recibe
     */
	public ClientThread(Socket clientSocket, String command) {
		this.clientSocket = clientSocket;
		this.command = command;
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
						
					}
					case "insertar" -> {
						
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
